package com.bitMiners.pdf.repositories.impl;

import com.bitMiners.pdf.domain.Answer;
import com.bitMiners.pdf.exceptions.PdfApiException;
import com.bitMiners.pdf.repositories.AnswerRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class AnswerRepositoryImpl implements AnswerRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public Integer add(Answer answer) {
        return (Integer) sessionFactory.getCurrentSession().save(answer);
    }

    public void delete(Integer id) {
        Query query = sessionFactory.getCurrentSession().createQuery("delete from Answer a where a.id=:id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        if (result < 1) {
            throw new PdfApiException("Error while deleting answer with id: " + id, 500);
        }
    }

    public Answer update(Answer answer) {
        Query query = sessionFactory.getCurrentSession().createQuery("update Answer a set a.answerContent=:content where a.id=:id");
        query.setParameter("content", answer.getAnswerContent());
        query.setParameter("id", answer.getId());
        int result = query.executeUpdate();
        if (result < 1) {
            throw new PdfApiException("Error while updating answer with id: " + answer.getId(), 500);
        }
        return answer;
    }

    public Answer findOne(Integer id) {
        return sessionFactory.getCurrentSession().get(Answer.class, id);
    }

    public List<Answer> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

}
