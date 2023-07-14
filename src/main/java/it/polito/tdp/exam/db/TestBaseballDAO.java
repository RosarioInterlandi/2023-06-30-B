package it.polito.tdp.exam.db;

import java.util.List;

import it.polito.tdp.exam.model.People;

public class TestBaseballDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BaseballDAO dao = new BaseballDAO();

		List<People> players = dao.readAllPlayers();
		List<Integer> v = dao.getVertici("Chicago Cubs");
		System.out.println(v.size());
		int ciao =dao.getSalaryTeamYear(2000, "Chicago Cubs");
		int mondo=dao.getSalaryTeamYear(1999, "Chicago Cubs");
		System.out.println(Math.abs(ciao - mondo));
	}

}
