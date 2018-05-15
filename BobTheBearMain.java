import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Locale.Category;
import java.util.concurrent.CopyOnWriteArrayList;

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
			} else if (NoOfSalmons == 2) {
				System.out.println(2);
			} else {
				System.out.println(NoOfSalmonsCatched(salmonLengthArr,
						salmonTimeArr, tailArr));
			}
		}
		scan.close();
	}

	private static int NoOfSalmonsCatched(Long[] salmonLengthArr,
			Long[] salmonHeadArr, Long[] tailArr) {

		List<Long> headList = new ArrayList<Long>();
		List<Long> tailList = new ArrayList<Long>();

		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < salmonHeadArr.length; i++) {
			long head1 = salmonHeadArr[i];
			long tail1 = tailArr[i];
			int catchCount = 0;
			for (int j = 0; j < salmonHeadArr.length; j++) {
				long head2 = salmonHeadArr[j];
				long tail2 = tailArr[j];
				int tempCount = 0;
				for (long q = head1; q <= tail1; q++) {
					for (long p = head2; p <= tail2; p++) {
						if (q == p) {
							tempCount++;
						}
					}

				}
				if (tempCount > 0) {
					catchCount++;
				}

			}
			set.add(catchCount);
		}

		List<Integer> al = new ArrayList<Integer>(set);
		System.out.println(al);
		if (al.size() > 1) {

			if (al.get(0) == 1 && al.get(1) == 1) {
				return 2;
			} else {
				return al.get(0) + al.get(1);
			}
		}
		return 0;
	}
	/*
	 * // timeArr == headArr public static int NoOfSalmonsCatched(Long[]
	 * salmonLengthArr, Long[] salmonHeadArr, Long[] tailArr) {
	 * 
	 * List<List<Long>> allList1 = new ArrayList<List<Long>>();
	 * 
	 * 
	 * for (int i = 0; i < salmonHeadArr.length; i++) { List<Long>
	 * individualList = new ArrayList<Long>(); Long h = salmonHeadArr[i]; Long t
	 * = tailArr[i]; for (Long j = h; j <= t; j++) { individualList.add(j); }
	 * allList1.add(individualList); }
	 * 
	 * Collections.sort(allList1, new Comparator<List<?>>() {
	 * 
	 * @Override public int compare(List<?> o1, List<?> o2) { return
	 * Integer.valueOf(o1.size()).compareTo(o2.size()); } });
	 * 
	 * List<List<Long>> allList2 = new CopyOnWriteArrayList<List<Long>>(
	 * allList1);
	 * 
	 * List<Integer> countList = new ArrayList<Integer>(); for (List<Long> list1
	 * : allList1) { int count = 0; for (List<Long> list2 : allList2) {
	 * List<Long> common = new ArrayList<Long>(list2); if
	 * (!Collections.disjoint(common, list1)) { count++; allList2.remove(list2);
	 * } } countList.add(count); } Collections.sort(countList);
	 * Collections.reverse(countList); System.out.println(countList); if
	 * (countList.get(0) == salmonHeadArr.length) { return countList.get(0); }
	 * else { return countList.get(0) + countList.get(1); }
	 * 
	 * }
	 */
}
