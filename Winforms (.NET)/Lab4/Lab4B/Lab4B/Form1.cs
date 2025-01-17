/*
 * I, Devang Bhojane, 000901300 certify that this material is my original work.  
 * No other person's work has been used without due acknowledgement.
 */


using System;
using System.Collections.Generic;
using System.IO;
using System.Windows.Forms;

namespace Lab4b
{
    /// <summary>
    /// The main form class for the application.
    /// </summary>
    public partial class Form1 : Form
    {
        private Stack<String> reversedTagStack; // Stack to store reversed HTML tags
        List<string> tempList; // Temporary list used for sorting
        public string DATA_FILE; // Path of the loaded data file

        /// <summary>
        /// Default constructor for Form1.
        /// Initializes the form components and sets default values.
        /// </summary>
        public Form1()
        {
            InitializeComponent();
        }

        /// <summary>
        /// Checks if the stack of HTML tags is balanced.
        /// </summary>
        /// <param name="stack">Stack of HTML tags</param>
        /// <returns>True if balanced, false otherwise</returns>
        private bool balancedStack(Stack<string> stack)
        {
            Stack<string> closingTags = new Stack<string>(); // Stack to store closing tags

            foreach (string tag in stack)
            {
                if (IsClosingTag(tag))
                {
                    closingTags.Push(tag);
                }
                else if (IsOpeningTag(tag))
                {
                    if (closingTags.Count == 0)
                    {
                        return false; // More closing tags than opening tags
                    }

                    string closingTag = closingTags.Pop();

                    if (IsMatchingPair(closingTag, tag))
                    {

                        return true; // Opening and closing tags don't match
                    }
                }
            }

            // If the closingTags stack is empty, then tags are balanced
            return closingTags.Count == 0;
        }

        /// <summary>
        /// Checks if a tag is an opening tag.
        /// </summary>
        /// <param name="tag">HTML tag</param>
        /// <returns>True if an opening tag, false otherwise</returns>
        private bool IsOpeningTag(string tag)
        {
            // Exclude specific tags from being treated as opening tags
            string[] excludedTags = { "<img", "<br", "<hr", "<!", "<input", "<--!" };

            foreach (var excludedTag in excludedTags)
            {
                if (tag.StartsWith(excludedTag, StringComparison.OrdinalIgnoreCase))
                {
                    return false; // Excluded tag, not treated as an opening tag
                }
            }

            // For simplicity, assume that any other tag starting with '<' is an opening tag
            return tag.StartsWith("<");
        }

        /// <summary>
        /// Checks if a tag is a closing tag.
        /// </summary>
        /// <param name="tag">HTML tag</param>
        /// <returns>True if a closing tag, false otherwise</returns>
        private bool IsClosingTag(string tag)
        {
            return tag.StartsWith("</");
        }

        /// <summary>
        /// Checks if an opening tag and a closing tag form a matching pair.
        /// </summary>
        /// <param name="openingTag">Opening HTML tag</param>
        /// <param name="closingTag">Closing HTML tag</param>
        /// <returns>True if a matching pair, false otherwise</returns>
        private bool IsMatchingPair(string openingTag, string closingTag)
        {
            // Extract tag names by removing '<' and '</' from the tags
            string openingTagName = openingTag.TrimStart('<').TrimEnd('>');
            string closingTagName = closingTag.TrimStart('<', '/').TrimEnd('>');

            // Remove leading '/' from the closing tag
            if (closingTagName.StartsWith("/"))
            {
                closingTagName = closingTagName.Substring(1);
            }

            // Compare the tag names
            return string.Equals(openingTagName, closingTagName, StringComparison.OrdinalIgnoreCase);
        }

        /// <summary>
        /// Sorts the stack of HTML tags in reverse order.
        /// </summary>
        /// <param name="stack">Stack of HTML tags</param>
        private void SortStack(Stack<string> stack)
        {
            tempList = new List<string>(stack);
            tempList.Sort((x, y) => String.Compare(y, x)); // Sort in descending order

            stack.Clear();

            foreach (string tag in tempList)
            {
                stack.Push(tag);
            }
        }

        /// <summary>
        /// Displays the balance status of HTML tags.
        /// </summary>
        /// <param name="isBalanced">True if tags are balanced, false otherwise</param>
        private void DisplayStatus(bool isBalanced)
        {
            if (isBalanced)
            {
                answerLabel.Text = "HTML container tags are balanced!";
            }
            else
            {
                answerLabel.Text = "HTML container tags are not balanced!";
            }
        }

        /// <summary>
        /// Event handler for loading a file.
        /// </summary>
        private void loadFileToolStripMenuItem_Click_1(object sender, EventArgs e)
        {
            OpenFileDialog openFileDialog = new OpenFileDialog();
            openFileDialog.Filter = "HTML Files|*.html;*.htm|All Files|*.*";

            if (openFileDialog.ShowDialog() == DialogResult.OK)
            {
                DATA_FILE = openFileDialog.FileName;
                answerLabel.Text = "File :" + DATA_FILE + " loaded";
            }
        }

        /// <summary>
        /// Event handler for checking HTML tags in the loaded file.
        /// </summary>
        private void checkTagsToolStripMenuItem_Click_1(object sender, EventArgs e)
        {
            try
            {
                reversedTagStack = new Stack<string>();
                using (StreamReader reader = new StreamReader(DATA_FILE))
                {
                    while (!reader.EndOfStream)
                    {
                        char currentChar = (char)reader.Read();
                        string currentTag = "";

                        if (currentChar == '<')
                        {
                            currentTag += currentChar;

                            char nextChar;
                            do
                            {
                                nextChar = (char)reader.Read();
                                currentTag += nextChar;
                            } while (nextChar != '>');

                            reversedTagStack.Push(currentTag);
                        }
                    }
                }

                // Check if the stack is balanced before sorting
                bool isBalanced = balancedStack(reversedTagStack);

                // Display each tag in the ListBox
                answerListbox.Items.Clear();
                foreach (string tag in reversedTagStack)
                {
                    if (tag[1] == '/')
                    {
                        answerListbox.Items.Add("Found Closing Tag: " + tag);
                    }
                    else
                    {
                        answerListbox.Items.Add("Found Opening Tag: " + tag);
                    }

                }

                // Display the balance status
                DisplayStatus(isBalanced);

                // Sort the stack after checking balance
                SortStack(reversedTagStack);
            }
            catch (IOException ex)
            {
                MessageBox.Show($"Error reading file: {ex.Message}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }
    }
}


