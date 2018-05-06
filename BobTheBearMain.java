import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BobTheBearMain {
	public static void main(String args[]) throws Exception {

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter Number of salmons -->  ");
		int NoOfSalmons = scan.nextInt();

		Long salmonLength[] = new Long[NoOfSalmons];
		for (int i = 0; i < salmonLength.length; i++) {
			salmonLength[i] = (long) scan.nextInt();
		}

		Long salmonTime[] = new Long[NoOfSalmons];
		for (int i = 0; i < salmonTime.length; i++) {
			salmonTime[i] = (long) scan.nextInt();
		}

		if (NoOfSalmons <= 1000 && salmonLength.length <= 1000000000
				&& salmonTime.length <= 1000000000) {
			if (NoOfSalmons == 1) {
				System.out.println(1);
			} else {
				System.out
						.println(NoOfSalmonsCatched(salmonLength, salmonTime));
			}
		}
		// System.out.println("Ans --> " + NoOfSalmonsCatched(salmonTime));
		scan.close();
	}

	public static int NoOfSalmonsCatched(Long[] salmonLength, Long[] salmonTime) {
		int count = 0;
		List<Long> lst = Arrays.asList(salmonTime);
		for (long integer : lst) {
			int occur = Collections.frequency(lst, integer);
			if (occur == 1) {
				count++;
			} else if (occur >= 2) {
				count++;
			}
		}
		return count;
	}
}