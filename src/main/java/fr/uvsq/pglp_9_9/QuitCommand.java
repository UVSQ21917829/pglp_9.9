package fr.uvsq.pglp_9_9;

public class QuitCommand implements Command {

	@Override
	public void execute() {
		System.exit(0);

	}

}
