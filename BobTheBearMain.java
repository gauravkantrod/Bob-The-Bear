import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
				List<List<Long>> allFishList = new CopyOnWriteArrayList<List<Long>>();
				for (int i = 0; i < salmonLengthArr.length; i++) {
					List<Long> individualFishList = new ArrayList<Long>();
					long head = salmonTimeArr[i];
					long tail = tailArr[i];
					for (long j = head; j <= tail; j++) {
						individualFishList.add(j);
					}
					allFishList.add(individualFishList);
				}
				Collections.sort(allFishList, new Comparator<List<?>>() {

					@Override
					public int compare(List<?> o1, List<?> o2) {
						return Integer.valueOf(o1.size()).compareTo(o2.size());
					}
				});

				System.out.println(NoOfSalmonsCatched(allFishList));
			}
		}
		scan.close();
	}

	private static Long NoOfSalmonsCatched(List<List<Long>> allFishList) {

		List<List<List<Long>>> finalList = new CopyOnWriteArrayList<List<List<Long>>>();
		List<Long> cnLtr = new CopyOnWriteArrayList<Long>();
		// System.out.println(allFishList);
		for (List<Long> fishList1 : allFishList) {
			Long count = 0l;
			List<List<Long>> refLsit = new CopyOnWriteArrayList<List<Long>>();
			for (List<Long> fishList2 : allFishList) {
				if (!Collections.disjoint(fishList1, fishList2)) {
					count++;
					refLsit.add(fishList2);
					allFishList.remove(fishList2);
				}
			}
			allFishList.remove(fishList1);

			if (count > 0) {
				cnLtr.add(count);
				finalList.add(refLsit);

			}

		}
	//	System.out.println(cnLtr);
	//	System.out.println(finalList);
		long l = fishCatch(finalList, cnLtr);

		return l;
	}

	private static Long fishCatch(List<List<List<Long>>> parentList,
			List<Long> fishTogetherCount) {

		List<Long> countLtr = new ArrayList<Long>();
		List<Long> ref1List = new ArrayList<Long>();

		List<List<Long>> refList1 = new ArrayList<List<Long>>();
		List<List<Long>> refList2 = new ArrayList<List<Long>>();

		for (List<List<Long>> p1 : parentList) {
			for (List<List<Long>> p2 : parentList) {
				if (!p1.equals(p2)) {
					int indexOfP1 = parentList.indexOf(p1);
					int indexOfP2 = parentList.indexOf(p2);
					Long countOfFishAtP1 = fishTogetherCount.get(indexOfP1);
					Long countOfFishAtP2 = fishTogetherCount.get(indexOfP2);

					if (countOfFishAtP1 > countOfFishAtP2) {

						for (List<Long> p11 : p1) {
							for (List<Long> p22 : p2) {
								if (!Collections.disjoint(p11, p22)
										&& !ref1List.equals(p22)) {
									countOfFishAtP1++;
									countOfFishAtP2--;
									/*
									 * fishTogetherCount.remove(indexOfP1);
									 * fishTogetherCount.remove(indexOfP2);
									 * fishTogetherCount.add(indexOfP1,
									 * countOfFishAtP1);
									 * fishTogetherCount.add(indexOfP2,
									 * countOfFishAtP2);
									 */
									ref1List = p22;
									p2.remove(p22);
									p1.remove(p11);
									countLtr.add(countOfFishAtP1);
									countLtr.add(countOfFishAtP2);
								}
							}
						}
					} else if (countOfFishAtP1 < countOfFishAtP2) {
						for (List<Long> p11 : p1) {
							for (List<Long> p22 : p2) {
								if (!Collections.disjoint(p11, p22)
										&& !ref1List.equals(p22)) {
									countOfFishAtP1--;
									countOfFishAtP2++;
									/*
									 * fishTogetherCount.remove(indexOfP1);
									 * fishTogetherCount.remove(indexOfP2);
									 * fishTogetherCount.add(indexOfP1,
									 * countOfFishAtP1);
									 * fishTogetherCount.add(indexOfP2,
									 * countOfFishAtP2);
									 */
									ref1List = p22;
									p2.remove(p22);
									p1.remove(p11);
									countLtr.add(countOfFishAtP1);
									countLtr.add(countOfFishAtP2);
								}
							}
						}
					}
				} /*
				 * else if (p1.equals(p2) && !refList1.equals(p1) &&
				 * !refList2.equals(p2)) {
				 * countLtr.add(fishTogetherCount.get(parentList.indexOf(p1)));
				 * refList1 = p1; refList2 = p2; }
				 */
			}
		}
	//	System.out.println(parentList);
	//	System.out.println(fishTogetherCount);

		Collections.sort(countLtr, new Comparator<Long>() {

			@Override
			public int compare(Long arg0, Long arg1) {
				// TODO Auto-generated method stub
				return arg1.compareTo(arg0);
			}
		});
		//System.out.println(countLtr);
		return countLtr.get(0) + countLtr.get(1);

	}
}
