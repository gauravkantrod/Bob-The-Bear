import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class BobTheBearMain {
	public static void main(String args[]) throws Exception {

		Scanner scan = new Scanner(System.in);
		// System.out.println("Enter Number of salmons -->  ");
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

		Map<Long, List<List<Long>>> map = new ConcurrentHashMap<Long, List<List<Long>>>();

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
				map.put(count, refLsit);
			}

		}
		long l = fishCatch(map);

		return l;
	}

	private static Long fishCatch(Map<Long, List<List<Long>>> map) {
		// System.out.println("oriMap" + map);

		List<Long> countList = new ArrayList<Long>();
		List<Long> refVal1 = new CopyOnWriteArrayList<Long>();
		List<Long> refVal2 = new CopyOnWriteArrayList<Long>();

		Map<Long, List<List<Long>>> refMap = new ConcurrentHashMap<Long, List<List<Long>>>();

		for (Long k1 : map.keySet()) {
			for (Long k2 : map.keySet()) {
				if (map.get(k1) != null && map.get(k2) != null) {
					if (!map.get(k1).equals(map.get(k2))) {

						if (k2 > k1) {
							Long minKey = k1;
							Long maxKey = k2;
							List<List<Long>> k1List = new CopyOnWriteArrayList<List<Long>>();
							List<List<Long>> k2List = new CopyOnWriteArrayList<List<Long>>();
							k1List = map.get(k1);
							k2List = map.get(k2);
							if (k1List != null && k2List != null) {
								for (List<Long> l1 : k1List) {

									for (List<Long> l2 : k2List) {
										if (!Collections.disjoint(l1, l2)
												&& !refVal1.equals(l1)
												&& !refVal2.equals(l2)) {
											minKey--;
											maxKey++;
											map.remove(k1);
											map.remove(k2);
											k1List.remove(l1);
											k2List.remove(l2);
											refMap.put(minKey, k1List);
											refMap.put(maxKey, k2List);
											refVal1 = l1;
											refVal2 = l2;
											countList.add(minKey);
											countList.add(maxKey);

										}
									}
								}
							}
						} else if (k1 > k2) {
							Long minKey = k2;
							Long maxKey = k1;
							List<List<Long>> k1List = new CopyOnWriteArrayList<List<Long>>();
							List<List<Long>> k2List = new CopyOnWriteArrayList<List<Long>>();
							k1List = map.get(k1);
							k2List = map.get(k2);
							if (k1List != null && k2List != null) {
								for (List<Long> l1 : k1List) {

									for (List<Long> l2 : k2List) {
										if (!Collections.disjoint(l1, l2)
												&& !refVal1.equals(l1)
												&& !refVal2.equals(l2)) {
											minKey--;
											maxKey++;
											map.remove(k1);
											map.remove(k2);
											k1List.remove(l1);
											k2List.remove(l2);
											refMap.put(minKey, k1List);
											refMap.put(maxKey, k2List);
											refVal1 = l1;
											refVal2 = l2;
											countList.add(minKey);
											countList.add(maxKey);

										}
									}
								}
							}
						} else if (k1 == k2) {
							countList.add(k1);
							map.remove(k1);
							map.remove(k2);
						}
					} else if (map.size() == 1
							&& map.get(k1).equals(map.get(k2))) {
						countList.add(k1);
					}

				}

			}
		}

		// System.out.println(refMap);

		Collections.sort(countList);
		Collections.reverse(countList);
		// System.out.println(countList);
		if (countList.size() == 1) {
			return countList.get(0);
		} else {
			return countList.get(0) + countList.get(1);
		}

	}
}
