package com.crio.qcontest.services;

// import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
// import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import com.crio.qcontest.entities.Contest;
import com.crio.qcontest.entities.Contestant;
import com.crio.qcontest.entities.DifficultyLevel;
import com.crio.qcontest.entities.Question;
import com.crio.qcontest.entities.User;
import com.crio.qcontest.repositories.IContestRepository;
import com.crio.qcontest.repositories.IContestantRepository;
import com.crio.qcontest.repositories.IQuestionRepository;
import com.crio.qcontest.repositories.IUserRepository;

public class ContestService{
    private final IContestantRepository contestantRepository;
    private final IContestRepository contestRepository;
    private final IQuestionRepository questionRepository;
    private final IUserRepository userRepository;

    public ContestService(IContestantRepository contestantRepository, IContestRepository contestRepository,
            IQuestionRepository questionRepository, IUserRepository userRepository) {
        this.contestantRepository = contestantRepository;
        this.contestRepository = contestRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    /**
     * Creates a new contest with specified parameters.
     * @param name Name of the contest.
     * @param level Difficulty level of the contest.
     * @param userId ID of the user creating the contest.
     * @param numOfQuestions Number of questions in the contest.
     * @return Created Contest object.
     * @throws RuntimeException if user is not found or requested questions cannot be fulfilled.
     */
    public Contest createContest(String name, DifficultyLevel level, Long userId, Integer numOfQuestions) {
        User creator = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User with an id: " + userId + " not found!"));
        
        List<Question> allQuestions = questionRepository.findByDifficultyLevel(level);
        if (allQuestions.size() < numOfQuestions) {
            throw new RuntimeException("Requested number of questions: " + numOfQuestions + " cannot be fulfilled!");
        }
        
        List<Question> selectedQuestions = pickRandomQuestions(allQuestions, numOfQuestions);
        
        Contest newContest = new Contest(name, level, creator, selectedQuestions);
        return contestRepository.save(newContest);
    }
    
    
    private List<Question> pickRandomQuestions(List<Question> questionList, Integer numQuestions) {
        Random rand = new Random();
        List<Question> tempList = new ArrayList<>();
        while (tempList.size() < numQuestions) {
            int randomIndex = rand.nextInt(questionList.size());
            Question selectedQuestion = questionList.get(randomIndex);
            if (!tempList.contains(selectedQuestion)) {
                tempList.add(selectedQuestion);
            }
        }
        return tempList;
    }

    public List<Contest> listContests(DifficultyLevel level) {
        if (level == null) {
            return contestRepository.findAll();
        } else {
            return contestRepository.findByDifficultyLevel(level);
        }
    }

    public Contestant attendContest(Long contestId, Long userId) {
        Contest contest = contestRepository.findById(contestId)
            .orElseThrow(() -> new RuntimeException("Contest with an id " + contestId + " not found!"));
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User with an id " + userId + " not found!"));
        
        if (contestantRepository.exists(contestId, userId)) {
            throw new RuntimeException("User is already registered for this contest.");
        }

        Contestant contestant = new Contestant(user, contest);
        return contestantRepository.save(contestant);
    }
    /**
     * Withdraws a contestant from a contest.
     * @param contestId ID of the contest.
     * @param userId ID of the user withdrawing from the contest.
     * @return True if contestant is successfully withdrawn, false otherwise.
     * @throws RuntimeException if contestant is not found.
     */
    public Boolean withdrawContest(Long contestId, Long userId) {
        if (!contestantRepository.exists(contestId, userId)) {
            throw new RuntimeException("Contestant not found!");
        }
        contestantRepository.deleteById(contestId, userId);
        return true;
    }

    /**
     * Executes a contest by selecting random questions for contestants and updating scores.
     * @param contestId ID of the contest.
     * @param userId ID of the user running the contest (contest creator).
     * @throws RuntimeException if contest is not found or user is not the creator.
     */
    public void runContest(Long contestId, Long userId) {
        Contest contest = contestRepository.findById(contestId)
            .orElseThrow(() -> new RuntimeException("Contest with an id " + contestId + " not found!"));
        if (!contest.getCreator().getId().equals(userId)) {
            throw new RuntimeException("Only the contest creator can run the contest!");
        }

        List<Contestant> contestantList = contestantRepository.findByContestId(contestId);
        for (Contestant contestant : contestantList) {
            List<Question> solvedQuestionsList = pickRandomQuestions(contest.getQuestions(), getRandomNumberInRange(0, contest.getQuestions().size()));
            solvedQuestionsList.forEach(contestant::addQuestion);

            User user = contestant.getUser();
            int currentContestPoints = solvedQuestionsList.stream().mapToInt(Question::getScore).sum();
            int newScore = calculateNewScore(user.getScore(), currentContestPoints, contest.getLevel());
            user.setScore(newScore);
        }
    }

    private int calculateNewScore(int currentScore, int contestPoints, DifficultyLevel level) {
        switch (level) {
            case LOW:
                return currentScore + (contestPoints - 50);
            case MEDIUM:
                return currentScore + (contestPoints - 30);
            case HIGH:
                return currentScore + contestPoints;
            default:
                throw new RuntimeException("Unknown difficulty level.");
        }
    }

    private int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.ints(min, (max + 1)).findFirst().getAsInt();
    }

    /**
     * Retrieves contest history sorted by contestant scores.
     * @param contestId ID of the contest.
     * @return List of contestants sorted by total score in descending order.
     * @throws RuntimeException if contest is not found.
     */
    public List<Contestant> contestHistory(Long contestId) {
        Contest contest = contestRepository.findById(contestId)
            .orElseThrow(() -> new RuntimeException("Contest with an id " + contestId + " not found!"));
        return contestantRepository.findByContestId(contestId).stream()
            .sorted((c1, c2) -> c2.getTotalScore().compareTo(c1.getTotalScore()))
            .collect(Collectors.toList());
    }
}
