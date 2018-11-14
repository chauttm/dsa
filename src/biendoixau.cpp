#include <iostream>
#include <string>
#include <cctype>
#include <set>

using namespace std;

bool solve(string& A, string& B) {
	int i = 0, j = 0;
	int last_touppered = -1;
	
	while (i < A.length() && j < B.length()) {
		if (A[i] == B[j]) j++;
		else if (toupper(A[i]) == B[j]) {
			last_touppered = j;
			j++; 
		}
		else if (j > 0 && A[i]==B[j-1] && last_touppered == j-1) last_touppered = -1;
		else if (isupper(A[i])) return false;
		i++;
	}
	if (j < B.length()) return false;
	for (; i < A.length(); i++) {
		if (isupper(A[i])) return false;
	}
	return true;
}

int main()
{
	int n;
	string A, B;
	
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> A >> B;
		cout << (solve(A, B) ? "YES":"NO") << endl;
	}
	
	return 0;
}
