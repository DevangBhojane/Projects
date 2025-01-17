using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

/*
 * I, Devang Bhojane, 000901300 certify that this material is my original work.  
 * No other person's work has been used without due acknowledgement.
 */

namespace Lab4A
{
    // Class representing the main program logic
    internal class Lab1
    {
        // Main method, entry point of the program
        static void Main(string[] args)
        {
            List<Employee> employees; // List to store employees

            // Read employee data from file
            Read(out employees);

            // Main program loop
            while (true)
            {
                string input = Menu(); // Display menu and get user input

                // Check if input is a valid integer
                if (int.TryParse(input, out int choice))
                {
                    // Check if choice is within the menu options
                    if (choice >= 1 && choice <= 5)
                    {
                        // Sort employees based on user choice and display table
                        Sort(employees, choice);
                        DisplayTable(employees);
                    }
                    else if (choice == 6) // Exit if choice is 6
                    {
                        break;
                    }
                    else // Invalid choice
                    {
                        Console.WriteLine("\n Invalid Choice - Try Again\n");
                    }
                }
                else // Invalid input
                {
                    Console.WriteLine("\nInvalid Choice - Try Again !!!\n");
                }
            }
        }

        // Method to display employee table
        private static void DisplayTable(List<Employee> employees)
        {
            Console.Clear();
            Console.WriteLine("Employee              Number    Rate  Hours  Gross Pay           Devang's Company");
            Console.WriteLine("====================  ======  ======  =====  =========           --------------");

            // Display each employee's information
            foreach (Employee employee in employees)
            {
                Console.WriteLine(employee);
            }

            Console.WriteLine();
        }

        // Method to display menu and get user input
        private static string Menu()
        {
            Console.WriteLine("1. Sort by Employee Name (ascending)");
            Console.WriteLine("2. Sort by Employee Number (ascending)");
            Console.WriteLine("3. Sort by Employee Pay Rate (descending)");
            Console.WriteLine("4. Sort by Employee Hours (descending)");
            Console.WriteLine("5. Sort by Employee Gross Pay (descending)");
            Console.WriteLine("\n6. Exit");
            Console.Write("\nEnter choice: ");

            return Console.ReadLine();
        }

        // Method to read employee data from file
        private static void Read(out List<Employee> employees)
        {
            employees = new List<Employee>(); // Initialize list

            try
            {
                // Open and read file line by line
                using (FileStream file = new FileStream("employees.txt", FileMode.Open, FileAccess.Read))
                using (StreamReader reader = new StreamReader(file))
                {
                    while (!reader.EndOfStream)
                    {
                        // Split each line into employee attributes and create Employee object
                        string[] exploded = reader.ReadLine().Split(',');
                        employees.Add(new Employee(exploded[0], int.Parse(exploded[1]), decimal.Parse(exploded[2]), double.Parse(exploded[3])));
                    }
                }
            }
            catch (IOException e) // Handle file not found error
            {
                Console.WriteLine("FILE NOT FOUND !!!\n");
                Environment.Exit(1); // Exit program
            }
        }

        // Method to sort employees based on user choice
        private static void Sort(List<Employee> employees, int choice)
        {
            switch (choice)
            {
                case 1: // Sort by Employee Name (ascending)
                    employees.Sort((emp1, emp2) => emp1.Name.CompareTo(emp2.Name));
                    break;

                case 2: // Sort by Employee Number (ascending)
                    employees.Sort((emp1, emp2) => emp1.Number.CompareTo(emp2.Number));
                    break;

                case 3: // Sort by Employee Pay Rate (descending)
                    employees.Sort((emp1, emp2) => emp2.Rate.CompareTo(emp1.Rate));
                    break;

                case 4: // Sort by Employee Hours (descending)
                    employees.Sort((emp1, emp2) => emp2.Hours.CompareTo(emp1.Hours));
                    break;

                case 5: // Sort by Employee Gross Pay (descending)
                    employees.Sort((emp1, emp2) => emp2.Gross.CompareTo(emp1.Gross));
                    break;
            }
        }
    }
}
