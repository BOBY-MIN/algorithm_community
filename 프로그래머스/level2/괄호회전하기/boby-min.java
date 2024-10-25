import java.util.Stack;

public int solution(String s) {

		if (s.isEmpty()) return 0;

		int answer = 0;
		Stack<Integer> checkStack = new Stack<Integer>();

		for (int i = 0; i < s.length(); i++) {

			if (check(s, checkStack)) {
				answer++;
			}

			s = s.substring(1, 2) + s.substring(2) + s.substring(0, 1);
		}

		return answer == s.length() ? 0 : answer;
	}

	public boolean check(String s, Stack<Integer> checkStack) {

		boolean checkResult = false;

		String[] check = s.split("");
		checkStack.clear();

		for (String arg : check) {
			switch (arg) {
			case "(":
				checkStack.push(0);
				break;

			case "[":
				checkStack.push(1);
				break;

			case "{":
				checkStack.push(2);
				break;

			case ")":
				if (!checkStack.empty() && checkStack.peek() == 0) {
					checkStack.pop();
				} else {
					checkResult = false;
					break;
				}
				break;

			case "]":
				if (!checkStack.empty() && checkStack.peek() == 1) {
					checkStack.pop();
				} else {
					checkResult = false;
					break;
				}
				break;

			case "}":
				if (!checkStack.empty() && checkStack.peek() == 2) {
					checkStack.pop();
				} else {
					checkResult = false;
					break;
				}
				break;

			default:
				checkResult = false;
				break;
			}
		}

		if (checkStack.empty())
			checkResult = true;

		return checkResult;
	}
