package model;

public class Message {
	public enum Status {
		SUCCESS, INFO, WARNING, DANGER
	}

	private Status status;
	private String message;

	public Message(Status status, String message) {
		this.status = status;
		this.message = message;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Message [status=" + status + ", message=" + message + "]";
	}

}
