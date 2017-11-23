package matala_0;

import java.util.ArrayList;

public abstract class myFilter implements Condition<String> {

	private NetWorks net;

	public myFilter(NetWorks net) {
		super();
		this.net = net;
	}

	// @Override public boolean test(Object s) {
	// return object.equals(s);
	// }

	// ���� ���� ����� ���� ��� ���� �������� object���� ����� ���� �"� ����
	// �����

	public ArrayList<NetWorks> filter(ArrayList<NetWorks> mekori, Condition<NetWorks> condition) {
		// ���� ���� ����� ����� ����� �� ������ ��������
		ArrayList<NetWorks> mesunan = new ArrayList<>();
		// ����� ������ ��� �� ����� ������ ����� ����� ����� �� ��� �� ���� ���
		// ��� �����, �� ������ �� ��� �� ����� ����� ����
		for (int i = 0; i < mekori.size(); i++) {
			if (condition.test(mekori.get(i))) {
				System.out.println("��� ���");
				mesunan.add(mekori.get(i));
			}
		}

		return mesunan;
	}
}
