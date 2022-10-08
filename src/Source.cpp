#include <string>
#include <fstream>
#include <iostream>
using namespace std;

// function to read from file
void readFile(string file_name, string* read_objects)
{
	string temp;
	fstream file(file_name, ios::in);
	int index = 0;
	while (file)
	{
		file >> temp;
		file >> temp;
		read_objects[index] = temp;
		index++;
	}
}

// function to take input from the user
string takeInput() {
	string input;
	cout << "Please enter a sentence (use letters, comma, period and space only): ";
	getline(cin, input);

	while (true)	// checking whether the input is correct or not and if its incorrect then taking input again
	{
		bool flag = 1;
		while (flag) {
			for (int i = 0; i < input.size(); i++)
			{
				if (input[i] != '.' && input[i] != ' ' && input[i] != ',' && (input[i] < 65 || (input[i] > 90 && input[i] < 97) || input[i] > 122))
				{
					// taking input again if the old input was incorrect
					cout << "Please enter a sentence (use letters, comma, period and space only): ";
					getline(cin, input);
					flag = 0;
					break;
				}
			}

			if (flag) {
				return input;	// returning the correct input to main
			}
		}
	}
}

// function to convert input to all lower case letters
string convertToLowercase(string input)
{
	for (int i = 0; i < input.size(); i++)
	{
		if (input[i] >= 65 && input[i] <= 90)
		{
			input[i] += 32;		// converting from lowercase to uppercase by adding 32
		}
	}
	return input;
}

// function to convert input to braille code
void convertToBraille(string input, string* read_obj, string* braille)
{
	for (int i = 0; i < input.size(); i++)
	{
		if (input[i] == ',')
		{
			braille[i] = read_obj[26];	// index 26 stores braille code of ','
		}
		else if (input[i] == '.')
		{
			braille[i] = read_obj[27];	// index 27 stores braille code of '.'
		}
		else if (input[i] == ' ')
		{
			braille[i] = read_obj[28];	// index 28 stores braille code of ' '
		}
		else
		{
			braille[i] = read_obj[input[i] - 97];	// finding respective index of alphabet in braille array
		}
	}
}

// function to print braille code in vertical order
void printBrailleVertical(string* braille, int size)
{
	for (int i = 0; i < size; i++)
	{
		for (int j = 0; j < 6; j += 2)
		{
			cout << braille[i][j];
			cout << braille[i][j + 1];
			cout << endl;
		}
		cout << "____________" << endl;
	}
}

// function to print braille code in horizontal order (credit part of assignment)
void printBrailleHorizontal(string* braille, int size)
{
	for (int j = 0; j < 6; j += 2)
	{
		for (int i = 0; i < size; i++)
		{
			cout << braille[i][j];
			cout << braille[i][j + 1];
			cout << " | ";
		}
		cout << endl;
	}
}

// printing final barchart of all the inputs
void printBarChart(int intArray[29])
{
	cout << endl << "Bar chart of frequency of occurrence: " << endl;
	for (int i = 0; i < 29; i++)
	{
		if (intArray[i] == 0)
			continue;
		if (i < 26)	// for all the alphabebts
		{
			char alphabet_to_display = i + 97;
			cout << alphabet_to_display << " | ";
			for (int j = 0; j < intArray[i]; j++)
			{
				cout << "*";	// '*' indicates number of occurrence
			}
		}
		else if (i == 26)	// for ','
		{
			cout << ", | ";
			for (int j = 0; j < intArray[i]; j++)
			{
				cout << "*";	// '*' indicates number of occurrence
			}
		}
		else if (i == 27)	// for '.'
		{
			cout << ". | ";
			for (int j = 0; j < intArray[i]; j++)
			{
				cout << "*";	// '*' indicates number of occurrence
			}
		}
		else if (i == 28)	// for ' '
		{
			cout << "  | ";
			for (int j = 0; j < intArray[i]; j++)
			{
				cout << "*";	// '*' indicates number of occurrence
			}
		}
		cout << endl;
	}
}

// function to find number of occurrences and making an integer array
void makeIntArray(string input) {
	int intArray[29] = { 0 };

	for (int i = 0; i < input.size(); ++i) {
		if (input[i] == ',') {
			intArray[26]++;
		}
		else if (input[i] == '.')
		{
			intArray[27]++;
		}
		else if (input[i] == ' ')
		{
			intArray[28]++;
		}
		else
		{
			intArray[input[i] - 97]++;
		}
	}

	printBarChart(intArray);
}


int main()
{
	string countFreq = "";
	string read_objects[29];
	readFile("braille_20210402_131148144.txt", read_objects);
	bool check = true;
	char in;

	// loop for asking about multiple inputs
	while (check) {
		string input = takeInput();
		input = convertToLowercase(input);
		string* braille = new string[input.size()];
		convertToBraille(input, read_objects, braille);
		printBrailleHorizontal(braille, input.size());
		countFreq += input;
		delete[] braille;

		cout << endl << "Would you like to enter another sentence Y/y or N/n? ";
		cin >> in;
		if (in == 'N' || in == 'n') {
			check = false;
		}
		cin.ignore();
	}

	makeIntArray(countFreq);
	return 0;
}
