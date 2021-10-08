package com.test.concurrent.case1;

import java.util.Optional;

public class Test1 {

	public static void main(String[] args) {
		Optional<String> optional = Optional.of("test");
		System.out.println(optional.orElse("no value"));

		System.out.println(optional.isPresent());

		User1 user = new User1("anna@gmail.com", "1234");
		user.setPosition("Developer");
		String position = Optional.ofNullable(user).flatMap(u -> u.getPosition()).orElse("default");
		System.out.println(position);
	}


}


class User1 {
	private String email;
	private String id;
	private String position;

	public User1(String email, String id) {
		this.email = email;
		this.id = id;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Optional<String> getPosition() {
		return Optional.ofNullable(position);
	}
}
