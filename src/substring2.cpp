#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

bool equivalent(string& s1, string& s2)
{
	sort(s1.begin(), s1.end());
	sort(s2.begin(), s2.end());
	return s1 == s2;
}

int count(string& s) {
	int count = 0;
	
	for (int len = 1; len <= s.length()/2; len++)
   		for (int start = 0; start + len + len - 1 < s.length(); start++) {
    		string s1 = s.substr(start, len);
    		string s2 = s.substr(start+len, len);
    		if (equivalent(s1,s2)) count++;
    }
	return count;
}

int main() {
	int n;
	string s;
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> s;	
		cout << count(s) << endl;
	}
	
	return 0;
}
