package com.etm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.etm.entity.Task;

public class taskDao {

	EntityManager entityManager = Persistence.createEntityManagerFactory("dbInfo").createEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();

	// Save Task
	public void saveTask(Task task) {

		entityTransaction.begin();
		entityManager.persist(task);
		entityTransaction.commit();
	}

	// Update Task
	public void updateTask(Task task) {

		entityTransaction.begin();
		entityManager.merge(task);
		entityTransaction.commit();
	}

	// Delete Task
	public void deleteTask(Task task) {

		entityTransaction.begin();
		entityManager.remove(task);
		entityTransaction.commit();
	}

	// Find Task by ID
	public Task findTask(int taskId) {
		return entityManager.find(Task.class, taskId);
	}

	// Find All Tasks
	public List<Task> findAllTask() {
		return entityManager.createQuery("SELECT task FROM Task task", Task.class).getResultList();
	}
}
