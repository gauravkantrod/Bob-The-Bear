import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
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

		List<List<Long>> allList1 = new CopyOnWriteArrayList<List<Long>>();

		for (int i = 0; i < salmonHeadArr.length; i++) {
			List<Long> individualList = new CopyOnWriteArrayList<Long>();
			Long h = salmonHeadArr[i];
			Long t = tailArr[i];
			for (Long j = h; j <= t; j++) {
				individualList.add(j);
			}
			allList1.add(individualList);
		}

		List<Integer> countist = new ArrayList<Integer>();

		for (int j = 0; j < salmonLengthArr.length; j++) {
			int fishCount = 0;
			for (List<Long> fishCo : allList1) {
				int tailindex = fishCo.size() - 1;
				// int fishCount = 0;
				if (fishCo.get(tailindex) < 0) {

					if (fishCo.contains(0)) {
						// fishCount++;
						allList1.remove(fishCo);
					}
					// countist.add(fishCount);

				} else {

					List<Long> tempLis = new ArrayList<Long>();
					if (fishCo.get(tailindex) > 0) {
						allList1.remove(fishCo);
						for (Long i : fishCo) {
							i--;
							tempLis.add(i);

						}
						allList1.add(tempLis);
					}
				}

			}

			for (List<Long> fishCo : allList1) {
				// int tailindex = fishCo.size() - 1;
				if (fishCo.contains(-1l)) {
					fishCount++;
					allList1.remove(fishCo);
					for (List<Long> fishCo2 : allList1) {
						if (fishCo2.contains(0l) && fishCo2.contains(-1l)) {
							fishCount++;
							allList1.remove(fishCo2);
						} else if (fishCo2.contains(0l)) {
							fishCount++;
							allList1.remove(fishCo2);
						} else if (!Collections.disjoint(fishCo, fishCo2)
								&& allList1.size() > 1) {
							fishCount++;
							allList1.remove(fishCo2);
							allList1.remove(fishCo);
						}
					}
				}
				if (fishCount > 0) {
					countist.add(fishCount);
				}
				fishCount = 0;
			}
		}

		// System.out.println(allList1);
		// System.out.println(countist);

		if (countist.get(0) == salmonHeadArr.length) {
			return countist.get(0);
		} else {
			return countist.get(0) + countist.get(1);
		}
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