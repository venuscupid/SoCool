
/**
 * The overall main program.
 * Each component may has its own main function.
 * 
 * @author yaoyao
 * @version 0.1
 *
 * HISTORY:
 * 		2013.10.4	created
 */
public class MainProgramSoCool
{
	public static String HELP_MESSAGE = "Usage:\t" + "java MainProgram <param>";

	public static void main(String[] args)
	{
		if((args == null) || (args.length == 0)){
			System.out.println(HELP_MESSAGE);
		}

	}

}
