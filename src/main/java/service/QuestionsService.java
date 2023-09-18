package service;

import entity.Question;
import repository.QuestionsRepo;

public class QuestionsService {

    private final QuestionsRepo questionsRepo;

    public QuestionsService(QuestionsRepo questionsRepo) {
        this.questionsRepo = questionsRepo;
    }


    public Question getQuestion(String requiredQuestion) {
        if (requiredQuestion != null) {
            try {
                return questionsRepo.getQuestionById(Integer.parseInt(requiredQuestion));
            } catch (Exception e) {
                throw new RuntimeException();
            }
        } else {
            return questionsRepo.getStartQuestion();
        }
    }
}
