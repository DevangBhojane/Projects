using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

/*
 * I, Devang Bhojane, 000901300 certify that this material is my original work.  
 * No other person's work has been used without due acknowledgement.
 */

namespace Lab4A
{
    // Employee class represents an employee with basic attributes and methods.
    internal class Employee
    {
        // Properties representing employee attributes
        public string Name { get; set; }        // Employee name
        public int Number { get; set; }         // Employee number
        public decimal Rate { get; set; }       // Pay rate per hour
        public double Hours { get; set; }       // Hours worked
        public decimal Gross => (Hours < 40) ? (decimal)Hours * Rate : (40.0m * Rate) + (((decimal)Hours - 40.0m) * Rate * 1.5m);
        // Gross pay calculated based on hours worked and pay rate, with overtime consideration

        // Default constructor
        public Employee() { }

        // Parameterized constructor to initialize employee attributes
        public Employee(string name, int number, decimal rate, double hours)
        {
            Name = name;
            Number = number;
            Rate = rate;
            Hours = hours;
        }

        // Method to compare employees based on their names
        public int CompareTo(Employee other)
        {
            return Name.CompareTo(other.Name);
        }

        // Method to represent Employee object as a string
        public override string ToString()
        {
            // Formatting employee information into a string
            return $"{Name,-20}  {Number:D5}  {Rate,6:C}  {Hours:#0.00}  {Gross,9:C}";
        }
    }
}
