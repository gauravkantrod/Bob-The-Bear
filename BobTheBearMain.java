import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class BobTheBearMain {
	public static void main(String args[]) throws Exception {

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter Number of salmons -->  ");
		int NoOfSalmons = scan.nextInt();

		Long salmonLengthArr[] = new Long[NoOfSalmons];
		for (int i = 0; i < salmonLengthArr.length; i++) {
			salmonLengthArr[i] = (long) scan.nextInt();
		}

		Long salmonTimeArr[] = new Long[NoOfSalmons];
		for (int i = 0; i < salmonTimeArr.length; i++) {
			salmonTimeArr[i] = (long) scan.nextInt();
		}

		Long tailArr[] = new Long[NoOfSalmons];
		for (int i = 0; i < salmonTimeArr.length; i++) {
			tailArr[i] = salmonTimeArr[i] + salmonLengthArr[i];
		}

		if (NoOfSalmons <= 1000 && salmonLengthArr.length <= 1000000000
				&& salmonTimeArr.length <= 1000000000 && NoOfSalmons >= 1
				&& salmonLengthArr.length >= 1 && salmonTimeArr.length >= 0) {
			if (NoOfSalmons == 1) {
				System.out.println(1);
			} else {
				System.out.println(NoOfSalmonsCatched(salmonLengthArr,
						salmonTimeArr, tailArr));
			}
		}
		scan.close();
	}

	// timeArr == headArr
	public static int NoOfSalmonsCatched(Long[] salmonLengthArr,
			Long[] salmonHeadArr, Long[] tailArr) {

		List<List<Long>> allList1 = new ArrayList<List<Long>>();

		for (int i = 0; i < salmonHeadArr.length; i++) {
			List<Long> individualList = new ArrayList<Long>();
			Long h = salmonHeadArr[i];
			Long t = tailArr[i];
			for (Long j = h; j <= t; j++) {
				individualList.add(j);
			}
			allList1.add(individualList);
		}

		Collections.sort(allList1, new Comparator<List<?>>() {
			@Override
			public int compare(List<?> o1, List<?> o2) {
				return Integer.valueOf(o1.size()).compareTo(o2.size());
			}
		});
		System.out.println(allList1);
		List<List<Long>> allList2 = allList1;
		List<Integer> countList = new ArrayList<Integer>();
		/*
		 * if (salmonHeadList.contains(0)) { count =
		 * Collections.frequency(salmonHeadList, 0); chance--; } else if (chance
		 * > 0) {
		 */
		for (List<Long> list1 : allList1) {
			int count = 0;
			for (List<Long> list2 : allList2) {
				if (!Collections.disjoint(list1, list2)) {
					count++;
				}
			}
			countList.add(count);
		}
		// }
		Collections.sort(countList);
		Collections.reverse(countList);
		System.out.println(countList);
		System.out.println(countList.get(0));
		System.out.println(countList.get(1));
		if(countList.get(0) == salmonHeadArr.length){
			return countList.get(0);
		}else {
			return countList.get(0) + countList.get(1);
		}
		
	}

}
