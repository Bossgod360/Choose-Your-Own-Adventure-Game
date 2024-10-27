import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
/*
Choose-Your-Own-Adventure (Harry Potter Edition)

This is a choose your own adventure game adpatation from the world of Harry Potter,
in this game you will get a display where you choose your descisions and progress through a thrilling story
of "The Forbbiden Book of Spells" Hope you have fun! : )

Written by Aryan Khimani
Last Updated: April 9, 2024
*/

public class Main {
    public static void main(String[] args) {
        // Creates the frame for the title screen
        JFrame openingFrame = createFrame();
        //Adds the starting picture for the title screen
        addHogwarts(openingFrame);
        //Adds the startbutton that will take you to the method named in the parameter
        //Also useful for debugging or for checking certain paths without going through the entire game
        addStartButton(openingFrame,"question1");
        //Sets the frame as visible
        openingFrame.setVisible(true);


    }
    /**
     * Creates a JFrame for the opening screen for the game.
     * The frame is customized with a title, background color, size relative to the screen size,
     * and positioned at the center of the screen.
     * @return The JFrame object for the opening screen.
     */
    public static JFrame createFrame() {
        //Creates the Frame and sets the title of the frame
        JFrame openingFrame = new JFrame("The Forbidden Book of Spells");

        //Sets the background colour of the frame
        openingFrame.getContentPane().setBackground(Color.BLACK);

        //This gets a tool to get the screen size of the laptop to set the size relative to it
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        //Gets the width relative to the screen size
        int width = (int) (screenSize.width * 0.6);

        //Gets the height relative to the screen size
        int height = (int) (screenSize.height * 0.5);

        //Sets the frame to the size based on the above
        openingFrame.setSize(width, height);

        //Sets the location of when the screen opens to the middle
        openingFrame.setLocationRelativeTo(null);

        // Disables the default layout manager
        openingFrame.setLayout(null);

        //Sets the default close operation for the frame
        openingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Returns the configured JFrame object
        return openingFrame;
    }
    /**
     * Customizes the appearance of a JButton by setting its foreground colour to white,
     * border color to white, and making its content area transparent.
     *
     * @param button The JButton to be customized.
     */
    public static void customizeButton(JButton button) {
        // Customize button appearance
        button.setForeground(Color.WHITE); //Sets the buttons text to white
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE)); //Sets the buttons border to white
        button.setContentAreaFilled(false); //Sets the buttons to have no fill
    }
    /**
     * Adds the Hogwarts logo to the opening screen JFrame.
     *
     * @param openingFrame The JFrame to which the Hogwarts logo will be added.
     */
    public static void addHogwarts(JFrame openingFrame) {
        // Loads the Hogwarts logo image
        ImageIcon imageIcon = new ImageIcon("Hogwarts-Logo.png");

        // Scales the image to fit half of the frame width while maintaining aspect ratio
        Image image = imageIcon.getImage().getScaledInstance(openingFrame.getWidth() / 2, -1, Image.SCALE_SMOOTH);

        // Updates the image icon with the scaled image
        imageIcon = new ImageIcon(image);

        // Creates a JLabel with the scaled image
        JLabel imageLabel = new JLabel(imageIcon);

        // Retrieves image dimensions and calculates positioning
        int imageWidth = imageIcon.getIconWidth();
        int imageHeight = imageIcon.getIconHeight();
        int xPosition = (openingFrame.getWidth() - imageWidth) / 2;
        int yPosition = (openingFrame.getHeight() - imageHeight) / 4;

        // Sets the bounds of the image label within the frame
        imageLabel.setBounds(xPosition, yPosition, imageWidth, imageHeight);

        // Adds the image label to the opening frame
        openingFrame.add(imageLabel);
    }

    /**
     * Adds a start button to the opening screen JFrame.
     *
     * @param openingFrame The JFrame to which the start button will be added.
     * @param methodName   The name of the method to be invoked when the button is clicked.
     */
    public static void addStartButton(JFrame openingFrame, String methodName) {
        // Create a JButton with "Start" label that will be displayed on the button
        JButton startButton = new JButton("Start");

        // Calculate button dimensions and positioning
        int buttonWidth = openingFrame.getWidth()/4;
        int buttonHeight = openingFrame.getHeight()/12;
        int xButton = (openingFrame.getWidth() - buttonWidth) / 2;
        int yButton = openingFrame.getHeight() / 2 + 100;

        // Set button bounds
        startButton.setBounds(xButton, yButton, buttonWidth, buttonHeight);

        // Customize button appearance
        customizeButton(startButton);

        // Add action listener to handle when the button gets clicked
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the current frame
                openingFrame.dispose();

                // Invoke the specified method with an empty ArrayList and 0 as the number of choices made
                allQuestion(new ArrayList<>(),-1,methodName);
            }
        });

        // Add the start button to the opening frame
        openingFrame.add(startButton);
    }
    /**
     * Adds a continue button to the specified JFrame.
     *
     * @param frame       The JFrame to which the continue button will be added.
     * @param methodName  The name of the method to be invoked when the button is clicked.
     * @param selections  The ArrayList containing the selections made.
     * @param numChoices  The number of choices made so far.
     */
    public static void addContinueButton(JFrame frame, String methodName,ArrayList<String> selections,int numChoices) {
        // Create a JButton with "Continue" label
        JButton continueButton = new JButton("Contiune");

        // Calculate button dimensions and positioning
        int buttonWidth = frame.getWidth() / 6;
        int buttonHeight = frame.getHeight() / 15;
        int xButton = (frame.getWidth() - buttonWidth) / 8-2;
        int yButton = frame.getHeight()/2+130;
        
        // Set button bounds
        continueButton.setBounds(xButton, yButton, buttonWidth, buttonHeight);

        // Customize button appearance
        customizeButton(continueButton);

        // Add action listener to handle when the button gets clicked
        continueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the current frame
                frame.dispose();

                // Invoke the specified method with selections and number of choices as parameters
                allQuestion(selections,numChoices,methodName);
            }
        });

        // Add the continue button to the frame
        frame.add(continueButton);
    }
    /**
     * Creates and customizes a JLabel to display the question for that section of the game.
     *
     * @param frame The JFrame where the question label will be displayed.
     * @param title The text to be displayed as the question.
     */
    public static void createQuestionLabel(JFrame frame,String title) {
        // Create a JLabel to display the question
        JLabel questionLabel = new JLabel(title);

        // Set font size relative to frame size
        float fontSize = Math.min(frame.getWidth(), frame.getHeight()) * 0.05f; // 5% of the minimum frame size
        questionLabel.setFont(questionLabel.getFont().deriveFont(fontSize)); // Set font size to the size calculated above
        questionLabel.setForeground(Color.WHITE);//Set the colour of the question to white

        // Set the position of the question label relative to frame size
        questionLabel.setBounds(frame.getWidth() / 10, frame.getHeight() / 10, frame.getWidth() / 2, frame.getHeight() / 10);

        // Add the question to the frame
        frame.add(questionLabel);
    }
    /**
     * Adds an image to the specified JFrame, a story section of the game.
     *
     * @param frame The JFrame to which the image will be added.
     * @param filename     The filename of the image to be displayed.
     */
    public static void addImage(JFrame frame, String filename) {
        // Create an ImageIcon from the specified filename
        ImageIcon imageIcon = new ImageIcon(filename);

        // Scale the image to fit half of the frame width while maintaining aspect ratio
        Image image = imageIcon.getImage().getScaledInstance(frame.getWidth() / 2, -1, Image.SCALE_SMOOTH);

        // Update the ImageIcon with the scaled image
        imageIcon = new ImageIcon(image);

        // Creates a JLabel to display the image
        JLabel imageLabel = new JLabel(imageIcon);

        // Retrieve image dimensions and calculate   positioning
        int imageWidth = imageIcon.getIconWidth();
        int imageHeight = imageIcon.getIconHeight();
        int xPosition = (3*frame.getWidth() - imageWidth) /6;
        int yPosition = (frame.getHeight() - imageHeight) / 4;

        // Set the bounds of the image label within the frame
        imageLabel.setBounds(xPosition, yPosition, imageWidth, imageHeight);

        // Add the image label to the frame
        frame.add(imageLabel);
    }
    /**
     * Displays text of the story on the specified JFrame.
     *
     * @param frame   The JFrame where the message will be displayed.
     * @param message The message to be displayed.
     */
    public static void showMessage(JFrame frame, String message) {
        // Create a JLabel with HTML content to ensure that line breaks get shown
        JLabel messageLabel = new JLabel("<html>" + message.replaceAll("\n", "<br>") + "</html>");

        // Sets the text colour to white
        messageLabel.setForeground(Color.WHITE);

        // Set the position and size of the message label relative to the frame size
        messageLabel.setBounds(frame.getWidth() / 10, frame.getHeight() / 8 , frame.getWidth() /4, frame.getHeight());

        // Sets the alignment to the top-left
        messageLabel.setVerticalAlignment(SwingConstants.TOP);
        messageLabel.setHorizontalAlignment(SwingConstants.LEFT);

        // Add the message label to the frame
        frame.add(messageLabel);
    }
    /**
     * Creates an array of JRadioButtons for selecting houses, customized with house names,
     * and positions them on the specified JFrame.
     *
     * @param frame      The JFrame where the radio buttons will be positioned.
     * @param options An array of strings containing the names of the houses.
     * @return An array of JRadioButtons representing the house selection options.
     */
    public static JRadioButton[] createRadioButtons(JFrame frame, String[] options) {
        // Create an array to hold the radio buttons
        JRadioButton[] radioButtons = new JRadioButton[options.length];

        // Create a ButtonGroup to ensure only one radio button can be selected at a time
        ButtonGroup group = new ButtonGroup();

        // Set the position of the radio buttons relative to frame size
        int buttonWidth = frame.getWidth() / 2;
        int buttonHeight = frame.getHeight() / 15;
        int buttonMargin = frame.getHeight() / 20;

        // Create and customize each radio button
        // Iterate over each option
        for (int i = 0; i < options.length; i++) {
            // Create a radio button with the name of option
            radioButtons[i] = new JRadioButton(options[i]);

            // Set position and size of the radio button
            radioButtons[i].setBounds(frame.getWidth() / 10, frame.getHeight() / 4 + i * (buttonHeight + buttonMargin), buttonWidth, buttonHeight);

            // Sets the texts colour to white
            radioButtons[i].setForeground(Color.WHITE);

            // Add button to the group
            // This is to ensure it behaves how I want it to
            group.add(radioButtons[i]);
        }

        // Return the array of created radio buttons
        return radioButtons;
    }
    /**
     * Creates and customizes a submit button.
     * This button collects user selections from radio buttons, adds them to an ArrayList,
     * then invokes a specified method with the selections, and number of choices made so far.
     *
     * @param frame        The JFrame where the submit button will be positioned.
     * @param radioButtons An array of JRadioButtons representing the selection options.
     * @param selections   An ArrayList to store user selections.
     * @param methodName   The name of the method to be invoked upon submission.
     * @param numChoices   The number of choices available.
     * @return The customized submit button.
     */
    public static JButton createSubmitButton(JFrame frame, JRadioButton[] radioButtons, ArrayList<String> selections, String methodName, int numChoices) {
        // Create a JButton with the text "Submit"
        JButton submitButton = new JButton("Submit");
        numChoices++;
        // Set the position of the submit button relative to frame size
        int buttonWidth = frame.getWidth() / 5;
        int buttonHeight = frame.getHeight() / 15;

        //Set the bounds of buttons
        submitButton.setBounds(frame.getWidth() / 10, frame.getHeight() / 4 + radioButtons.length * (buttonHeight + frame.getHeight() / 20), buttonWidth, buttonHeight);

        // Customize button appearance
        customizeButton(submitButton);

        // Add action listener to the submit button
        int finalNumChoices = numChoices;
        submitButton.addActionListener(new ActionListener() {
            // Define the actionPerformed method to handle button click events
            public void actionPerformed(ActionEvent e) {
                for (JRadioButton radioButton : radioButtons) {
                    if (radioButton.isSelected()) {
                        // Get the text of the selected radio button
                        String selected = radioButton.getText();

                        // Add the selected house to the ArrayList
                        selections.add(selected);

                        //Go to the next part of the game
                        allQuestion(selections, finalNumChoices, methodName);

                        // Close the previous frame
                        frame.dispose();

                        // Exit the loop since we found the selected radio button
                        break;
                    }
                }
            }
        });
        // Return the submit button
        return submitButton;
    }
    /**
     * Displays a story message, adds a continue button, and shows an image on the specified JFrame.
     *
     * @param frame         The JFrame where the story message, continue button, and image will be displayed.
     * @param message       The story message to be displayed.
     * @param nextQuestion  The name of the method representing the next question.
     * @param selections    An ArrayList containing selections made so far.
     * @param numChoices    The number of choices made so far.
     * @param imageName     The filename of the image to be displayed.
     */
    public static void displayStory(JFrame frame, String message, String nextQuestion, ArrayList<String> selections, int numChoices, String imageName) {
        // Display the story message
        showMessage(frame, message);

        // Adds a continue button
        addContinueButton(frame, nextQuestion, selections, numChoices);

        // Add an image to the frame
        addImage(frame, imageName);

        // Sets the frame to be visible
        frame.setVisible(true);
    }
    /**
     * Displays a story message, adds a continue button, and shows an image to the frame.
     *
     * @param frame         The JFrame where the story message, continue button, and image will be displayed.
     * @param options       An array of options for the multiple choice question
     * @param nextQuestion  The name of the method representing the next question.
     * @param selections    An ArrayList containing selections made so far.
     * @param numChoices    The number of choices made so far.
     * @param methodName    the name of the method to be invoked upon submitting the answer.
     */
    public static void displayQuestion(JFrame frame, String[] options, String theQuestion, ArrayList<String> selections, int numChoices, String methodName) {
        // Creates the question label and adds to frame
        createQuestionLabel(frame,theQuestion);

        // Create radio buttons for each options
        JRadioButton[] radioButtons = createRadioButtons(frame,options);

        // Adds radio buttons to the frame
        for (JRadioButton radioButton : radioButtons) {
            frame.add(radioButton);
        }

        // Create submit button and add to frame
        JButton submitButton = createSubmitButton(frame, radioButtons, selections,methodName,numChoices);

        // Adds submit button to the frame
        frame.add(submitButton);
        // Make the frame visible
        frame.setVisible(true);
        // Set default close operation to exit when the frame is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Sets the frame to be visible
        frame.setVisible(true);
    }
    /**
     * Main method that dictates the flow of the story based on the provided selections, number of choices, and next choice/story
     * Creates a JFrame and then uses a Hashmap (`story`) to link different sections of the story with the appropriate display method.
     * It also cotains all the String arrays for the choices to make it easier to locate within the code.
     *
     * @param selections    the list of selections made so far in the story
     * @param numChoices    the number of choices made so far in the story
     * @param methodName    the name of the method to be executed next in the story flow
     */
    public static void allQuestion(ArrayList<String> selections,int numChoices, String methodName){
        // Create a JFrame
        JFrame frame = createFrame();
        /* Print selections, numChoices, and methodName (optional)

        System.out.println(selections);
        System.out.println(numChoices);
        System.out.println(methodName);

         */

        // Arrays for the question's options/choices

        //Choose your House?
        String[] houseNames = {"Gryffindor", "Slytherin", "Hufflepuff", "Ravenclaw"};
        //Choose your Friend?
        String[] friendName1 = {"Astrid Fireheart", "Narcissa Darkwood", "Daisy Meadowsweet", "Edgar Eboncrest"};
        //Choose your next choice?
        String[] choices1 = {"Investigate Immediately", "Don’t care I have cream puffs to eat", "Play truth or dare", "Be a responsible student"};
        //Choose your lunch buddy?
        String[] friendName2 = {"Orion Carmicheal", "Valeria Livingstone", "Luna (Just Luna)"};
        //Choose your plan to break in?
        String[] dareOptions = { "Use cloak of invisibility", "Use your knowledge of magic", "Try to sneak in with the crowd during the morning", "Wait till 4:43 AM and use a crowbar"};
        //Next Plan of Action?
        String[] choices2 = { "Go to the Grand Staircase", "Go to the library","Go to Class and Talk to Teacher"};
        //What's the passcode?
        String[] passcode = {"Sarras Ézama - Parseltongue","Open Sesame","Alaris Alakaz - Aviaris tongue","Use your magic"};

        // The spells are based on your first choice of house : ) located in the method duelingSpells()

        // Creates the hashmap to store the story segments and actions assocatied with the actions
        // My reasoning for hasmap compared to switch statement or other atlernatives was the simplicty and the fact you could collapse them to make it easier to read, plus looking up in Hashmaps don't take to long thankfully : )
        // All are needed for diffrenet paths and lines in the story
        Map<String, Runnable> story = new HashMap<>();
        story.put("question1", () -> displayQuestion(frame, houseNames, "Choose your House?", selections, numChoices, "story2"));
        story.put("story2", () -> {
            String message = story2Message(selections, numChoices, houseNames);
            displayStory(frame, message, "question3", selections, numChoices, "The-Sorting-Hat.jpeg");
        });
        story.put("question3", () -> displayQuestion(frame, friendName1, "Choose your Friend?", selections, numChoices, "story4"));
        story.put("story4", () -> {
            String selection = selections.get(numChoices);
            String message = story4Message(selection);
            displayStory(frame, message, "story5", selections, numChoices, "The-Sorting-Hat.jpeg");
        });
        story.put("story5", () -> {
            String message = """
                As the days pass and you settle into life at Hogwarts, whispers of a forbidden treasure begin to circulate among the students. It is said that hidden deep within the vaults of Hogwarts lies the Forbidden Book of Spells – a book containing ancient knowledge and powerful incantations rumored to grant god-like abilities to any who possess it.

                Rumors spread like wildfire through the castle corridors, and soon, it becomes clear that someone intends to steal the book.""";
            displayStory(frame, message, "question6", selections, numChoices, "The-Sorting-Hat.jpeg");
        });
        story.put("question6", () -> {
            if (selections.getFirst().equals("Gryffindor")) {
                String message = """
                    With a daring spirit and a nose for adventure, you're drawn to the Forbidden Book like a Niffler to shiny objects.

                    Ignoring the warnings... LIKE ALL THE WARNINGS!

                     (Your Gryffindor its kinda your thing)

                     So you set off it's time for a magical investigation!""";
                displayStory(frame, message, "outsideCommonRoom", selections, numChoices, "The-Sorting-Hat.jpeg");
            }
            else {
                displayQuestion(frame,choices1,"Choose your next choice?",selections,numChoices,"story7");
            }
        });
        story.put("story7", () -> story7Message(frame, selections, numChoices));
        story.put("question7_1", () -> allQuestion(selections, numChoices, "outsideCommonRoom"));
        story.put("story7_2", () ->{
            String message = """
                You walk into The Great Hall and find that you are not the only one there
                
                There are 4 other people sitting all by themesleves, they all seem like they could need a friend to eat with.
                
                (But not share food JUST eat with you don't share your food EVER)
                
                So who will you choose?
                """;
            displayStory(frame, message, "question7_2_1", selections, numChoices, "The-Sorting-Hat.jpeg");
        });
        story.put("question7_2_1", () -> displayQuestion(frame,friendName2,"Choose your lunch buddy?",selections,numChoices,"story7_2_2"));
        story.put("story7_2_2", () ->{
            String message = story7_2Message(selections, numChoices);
            displayStory(frame, message, "story7_2_3", selections, numChoices, "The-Sorting-Hat.jpeg");
        });
        story.put("story7_2_3", () ->{
            String message = """
               You're in the middle of a lively chat when suddenly, you catch whispers in the air. It's like someone's softly humming a tune just for you. Then, out of nowhere, this cool, calm voice chimes in...
               
               Hey there, it's Rowena Ravenclaw sliding into your thoughts..great...myst...pow...destiny..............you
                
               You think its just all the cream puffs you been eating and carry on with your day. Actually before you do that you remeber you downloaded a game on your phone a COOKIE CLICKER!!! Time to play, you ready?
                """;
            displayStory(frame, message, "story7_2_4", selections, numChoices, "The-Sorting-Hat.jpeg");
        });
        story.put("story7_2_4", () ->{
            String message = """
               Once you press start timer you have 10 seconds to click on the button as many times as you can for a prize.
               
               Prize:
               
               "You don't get a cookie : (" - less than 10 clicks
               
               Plushie Cookie - more than 10 clicks
               
               Owl Prize- more than 50 clicks!!!
            
                """;
            displayStory(frame, message, "cookieClicker", selections, numChoices, "The-Sorting-Hat.jpeg");
        });
        story.put("cookieClicker", () -> cookieClicker(selections,numChoices));
        story.put("story7_3", () ->{
            String message = """
                        Your heart pounding with excitement!!!
                                                                                                               
                        "Dare break into the restricted section of the library."
                    
                        The words send a thrill down your spine. The restricted section, forbidden and mysterious, beckons with its secrets, but the consequences of getting caught loom ominously.
                                                                                                              
                        Do you accept the challenge and risk delving into the forbidden realm of knowledge, ALWAYS!
                                                                                                              
                        """;

            displayStory(frame, message, "question7_3_1", selections, numChoices, "The-Sorting-Hat.jpeg");
        });
        story.put("question7_3_1", () -> displayQuestion(frame,dareOptions,"Choose your plan to break in?",selections,numChoices,"story7_3_2"));
        story.put("story7_3_2", () -> story7_3_2Message( frame, selections, numChoices));
        story.put("story7_3_3_1", () ->{
            String message = """
                        [4:30 AM]
                      
                        You get a message to meet you outside your common room and you can see your friend who needs your help now to break into the restricted section of the library.
                        
                        You being a good friend (mostly cause you wanted to prove it to yourself) you agree to help them.
                        
                        So you head off to break into the library, oddly when you get there you find the door is already open!
                        """;
            displayStory(frame, message, "libraryBreakIn", selections, numChoices, "The-Sorting-Hat.jpeg");
        });
        story.put("story7_3_3_2", () ->{
            String message = """
                        You take a deep breath, feeling the rush of anticipation, the thrill of the heist. But just as you're about to, you freeze in disbelief. 
                         
                        ...
                         
                        ...
                         
                        The door, stands wide open, the universe truly has a twisted sense of humour. 😂
                        
                        """;
            displayStory(frame, message, "libraryBreakIn", selections, numChoices, "The-Sorting-Hat.jpeg");
        });
        story.put("libraryBreakIn", () ->{
            String message = """
                        In the library, amidst the dimly lit shelves, your eyes are drawn to a peculiar sight.There lies a book unlike any other. Its pages seem to shimmer with an ethereal glow, and its title, “V̴̺̜̯̝͖̗̏͛͆͋̿A̷̱̍̆͋̎̕͝ͅL̴̻͂̇͗̕U̵̥͂T̵̛̫̙̃̒S̸̛͖̼̼̭̜͉̐̆͂̒̀ ̷̡̟̬̟̰̞́͂̌Ǫ̶̦̻͈͎͗̎͜F̷͚͕̭͕̲͛̏͋̒̌ͅ ̷̢̪̮̦̝̙̾̂̚Ḧ̴̻́͑̀͘Ơ̶̙͔̟͚̐̄̇͒͌G̷͚͙̓͒̄̍̐͝Ẁ̷͉̓͒̕͘Ȁ̸̼R̴̯͋͂̂T̴̳͙͚͚́̐̈́͆͌S̸̢̛̱̪̪̀̋̋̈́͌", dances with arcane symbols and cryptic runes. 
                        
                        You flip through the pages and find that there is a location specified as the location for the valut of "The Forbidden Book of Spells" it says it's located in the in the caves near the water of the Grand Staircase of Hogwarts!
                        """;
            displayStory(frame, message, "outsideCommonRoom", selections, numChoices, "The-Sorting-Hat.jpeg");
        });
        story.put("story7_4", () ->{
            String message = """
                Your classmates are talking about a legendary spell: 
                
                "Vimulsum Incantate" 
                
                Supposedly, it grants the caster unlimited magical prowess, but there's a catch—it can only be used when granted permission by the ghost of Rowena Ravenclaw. 
                
                You keep that spell in mind cause you feel it might be useful in the future and practice saying it a couple of times.       
                """;
            displayStory(frame, message, "outsideCommonRoom", selections, numChoices, "The-Sorting-Hat.jpeg");
        });
        story.put("outsideCommonRoom", () ->{
            String message = outsideCommonFriend(selections, friendName2);
            displayStory(frame, message, "outsideDecide", selections, numChoices, "The-Sorting-Hat.jpeg");
        });
        story.put("outsideDecide", () ->{
            String message = """
               It is said that the Forbidden Book of Spells holds the key to unlocking the greatest mysteries of magic!" 
               
               So its decided you and your rag tag group of friends will find the book before someone else does.
               """;
            displayStory(frame, message, "question8", selections, numChoices, "The-Sorting-Hat.jpeg");
        });
        story.put("question8", () -> displayQuestion(frame,choices2,"Next Plan of Action?",selections,numChoices,"question8_1"));
        story.put("question8_1", () -> story8Message( frame, selections, numChoices));
        story.put("story8_2", () ->{
            String message = """
                You find the book quickly and start to read through it...You see location to spider dugenon, stone of healing, map to fountain of youth... voldmort's... nope just a bunch of useless things...
                
                You see there is a ton of different places to look but one stands out to you its in "Aviaris Tongue" the language of birds. Rowena Ravenclaw spoke it and so do you 
                
                It says to head to the body of water outside of the Grand Staircase!!! 
                """;
            displayStory(frame, message, "grandStaircase", selections, numChoices, "The-Sorting-Hat.jpeg");
        });
        story.put("story8_3", () ->{
            String message = """
                You reach your class and find Mr.Flitwick practing his spells furiously at target dummines, you notice him being VERY stressed.
                
                You see that his robe is muddy and.... is that blood on his shoes!
                
                You look around and notice a map with a cricle around the water near the Grand Staircase, you make up an excuse and leave for there!
                """;
            displayStory(frame, message, "grandStaircase", selections, numChoices, "The-Sorting-Hat.jpeg");
        });
        story.put("grandStaircase", () ->{
            String message = """
                Go to the Grand Staircase at night, you wait for your firends to arrive then head off to the water to find Hogwarts Valut!
                """;
            displayStory(frame, message, "theBigDoor", selections, numChoices, "The-Sorting-Hat.jpeg");
        });
        story.put("theBigDoor", () ->{
            String message = """
                You see a unique design on the rocky walls, you notice that its a lock you have to solve it!
                
                You notice symbols and glyphs, hinting at the passcode.You sense a profound connection to the natural world. The art of rippling energy, whispers of the wind and the call of the birds, trees, the sky.
                
                Your team comes up with four options of how to proceed.
                """;
            displayStory(frame, message, "question9", selections, numChoices, "The-Sorting-Hat.jpeg");
        });
        story.put("question9", () -> displayQuestion(frame,passcode,"What's the passcode?",selections,numChoices,"question9_1"));
        story.put("question9_1", () -> story9Message( frame, selections, numChoices));
        story.put("trappedEnding", () ->{
            String message = """
                11 minutes later...
                
                Still trapped within the cage, suddenly, the air around you crackles with dark energy. In a whirlwind of black and red, a figure materializes before you. His presence is an aura or pure evil that sends shivers down your spine. 
                
                Before you can react, he raises a hand, casting a spell that leaves you paralyzed. With a chilling grin, he disappers
                
                You lost, evil won and the wizard world is doomed!
                """;
            displayStory(frame, message, "credits", selections, numChoices, "The-Sorting-Hat.jpeg");
        });
        story.put("doorOpened", () ->{
            String message = """
                The rush inside towards the main vault your eyes lock onto the coveted book floating atop a podium! Before you can even begin to think... its Rowena Ravenclaw saying, "MOVE!!!"
                
                You just in time evade the dark spell hurled by the villain, your friends on the other hand were down. In a flash, the villain teleports to the podium, inches away from the Book of Forbidden Spells.
                
                With determination blazing in your eyes, you draw your wand, ready to engage in a duel!!!!  
                """;
            displayStory(frame, message, "duelingTime", selections, numChoices, "The-Sorting-Hat.jpeg");
        });
        story.put("duelingTime", () -> displayQuestion(frame,duelingSpells(selections, numChoices),"What's spell will you shoot?",selections,numChoices,"results"));
        story.put("results", () -> resultOfSpell( frame, selections, numChoices));
        story.put("endingLostDuel", () ->{
            String message = """
                The dread of losing kicks in you feel your soul quacking in fear of what is going to come...
                
                You get back on campus and you see it destroyed, fires, debris and rubble every where its the end of Hogwarts.
                
                Game Over (⋟﹏⋞)
                """;
            displayStory(frame, message, "credits", selections, numChoices, "The-Sorting-Hat.jpeg");
        });
        story.put("endingWinDuel", () ->{
            String message = """
                You get to Howgarts and rush to the office of the headmaster...
                
                Before you can get there you see the headmaster running towards you! He is screaming, "ARE YOU OKAY? I CAN'T BELIEVE A COUPLE OF FIRST YEARS COULD DEFEAT... ARE YOU OKAY?"
                
                We tell him what happens... the villian is captured and the wizard world is saved.
                
                YOU WON!!!
                """;
            displayStory(frame, message, "credits", selections, numChoices, "The-Sorting-Hat.jpeg");
        });
        story.put("credits", () -> creditsGoesTo());

        // This gets the method/actions associted with that part of the story.It also runs the code associted with it.
        story.get(methodName).run();
    }

    /**
     * Generates a message based on the house selection made during the sorting process.
     *
     * @param selections    the list of selections made so far in the story
     * @param numChoices    the number of choices available in the current question
     * @param houseNames    an array containing the names of the houses
     * @return message      - the generated message
     */
    public static String story2Message(ArrayList<String> selections, int numChoices, String[] houseNames) {
        // Initialize an empty message
        String message = "";

        // Iterate through the house names
        for (String house : houseNames) {
            // Check if the selected house matches any of the house names
            if (selections.get(numChoices).equals(house)) {
                // Construct the message based on the selected house
                message = "Your heart pounds with excitement as you await its decision. Finally, after what seems like an eternity, the Hat speaks:<br><br>" +
                        house + "!<br><br>" +
                        "Cheers erupt from the " + house + "'s table as you make your way to join your new housemates. The Great Hall buzzes with excitement as the Sorting Ceremony continues, but your thoughts are consumed by the adventures that lie ahead.";
                break;
            }
        }
        //Defenitly another way I just wanted to practice using String house : housenames type for loops : )
        // Return the generated message
        return message;
    }

    /**
     * Generates a message based on the selected friend's name - each friend has a unique opening towards you
     *
     * @param selection    the name of the selected friend
     * @return message     - the generated message
     */
    public static String story4Message(String selection) {
        // Initialize an empty message
        String message = "";
        // Switch statement to handle different friend selections
        // Cause there is a fixed number of options this works
        switch (selection) {
            case "Astrid Fireheart":
                message = "Hey, hey! \n\n Astrid Fireheart here, Gryffindor's resident firecracker!\n\n Sit with me, and I promise you'll never have a dull moment.\n\n Plus, who needs boring old textbooks when you can have epic tales of daring escapades?";
                break;
            case "Narcissa Darkwood":
                message = "Well, well, well, if it isn't the newest addition to Hogwarts. \n\nI'm Narcissa Darkwood, Slytherin's shiniest gem.\n\nStick with me, and you'll learn the fine art of getting what you want without anyone suspecting a thing. Care for a taste of the dark side?";
                break;
            case "Daisy Meadowsweet":
                message = "Hiya! \n\nDaisy Meadowsweet here, Hufflepuff's resident ray of sunshine. Sit with me, and I'll introduce you to the best snacks Hogwarts has to offer. \n\nPlus, I'll throw in some top-notch friendship and cuddly badger hugs. What do you say?";
                break;
            case "Edgar Eboncrest":
                message = "Greetings, seeker of knowledge! Edgar Eboncrest at your service, Ravenclaw's walking encyclopedia. \n\n Sit with me, and together we shall explore the deepest mysteries of the universe, solve mind-bending riddles, and maybe even crack a few jokes that only the cleverest minds can appreciate. Ready to unlock the secrets of the universe?";
                break;
            default:
                // Print error message for unknown choice
                System.out.println("Unknown choice: " + selection);
        }
        // This was before I started using """ , but didn't want to get rid of it cause I was proud to make "<html>" + message.replaceAll("\n", "<br>") + "</html>"
        // Return the generated message
        return message;
    }
    /**
     * Based on player's choice it displays a corresponding messages or actions, leading to different paths
     *
     * @param frame         the JFrame where the message will be displayed
     * @param selections    the list of selections made so far in the story
     * @param numChoices    the number of choices available in the current question or action
     */
    public static void story7Message(JFrame frame, ArrayList<String> selections, int numChoices)  {
        // Initialize an empty message
        String message = "";
        // Get the selection made by the player last
        String selection = selections.getLast();
        switch (selection) {
            case "Investigate Immediately":
                allQuestion(selections, numChoices,"outsideCommonRoom");
                break;
            case "Don’t care I have cream puffs to eat":
                message = """
                            With a carefree shrug, you go towards back towards the simple pleasures of life... Cream puffs!

                             Their fluffy pastry shells and promises of sweet indulgence call you towards The Great Hall.

                             With strides filled with a newfound purpose you set aside the whispers and go find yourself a well-deserved treat""";
                // Display the story message
                displayStory(frame, message, "story7_2", selections, numChoices, "The-Sorting-Hat.jpeg");
                break;
            case "Play truth or dare":
                message = """
                            Why tackle the mysteries of the Forbidden Book when you can unravel the secrets of your fellow classmates instead?

                            With a mischievous grin and a twinkle in your eye, you propose a game of Truth or Dare

                             It's just an innocent game what can even happen : )""";
                // Display the story message
                displayStory(frame, message, "story7_3", selections, numChoices, "The-Sorting-Hat.jpeg");
                break;
            case "Be a responsible student":
                message = """
                            While others chase after forbidden treasures, you choose the path of the diligent scholar.

                            Plus university isn't going to hire a dead or oh my even worse an EXPELLED student!!!

                            So you grab your books and head to spell class.""";
                // Display the story message
                displayStory(frame, message, "story7_4", selections, numChoices, "The-Sorting-Hat.jpeg");
                break;
            default:
                System.out.println("Unknown choice: " + selection);
        }
    }
    /**
     * Generates a message based on the selected friend's name after choosing to eat cream puffs.
     *
     * @param selections    the list of selections made so far in the story
     * @param numChoices    the number of choices available in the current question or action
     * @return message -    the generated message
     */
    public static String story7_2Message(ArrayList<String> selections, int numChoices) {
        String message = "";
        String selection = selections.getLast();
        switch (selection) {
            case "Orion Carmicheal":
                message = """
                        Hey, fellow cream puff crusaders!

                        Orion Carmichael in the house, or should I say, in the Great Hall? These cream puffs are like fluffy clouds of joy!

                        Cream puff high-five, anyone?""";
                break;
            case "Valeria Livingstone":
                message = """
                        Greetings, pastry pals!

                        Valeria Livingstone here, reporting for cream puff duty.

                        Just messing with you, my dad was in an Auror, he was very strict when it came to first introducing yourself to someone

                        Care to take a seat?""";
                break;
            case "Luna (Just Luna)":
                message = """
                        Hi I'm Lune, Just Luna : ) (idk what you were expecting)

                        Did you want a tramautic backstory? I'm normal

                        """;
                break;
            default:
                System.out.println("Unknown choice: " + selections);
        }
        return message;
    }
    /**
     * Implements a cookie-clicker game where players click on a circle.
     * Players have 10 seconds to click the circle as many times as possible.
     * After the timer ends, the method invokes the cookieClickerResults method to display the score.
     * The circle's color is determined based on the selected house: Gryffindor - red, Slytherin - green, Hufflepuff - yellow, Ravenclaw - blue!!!
     *
     * @param selections    the list of selections made so far in the story
     * @param numChoices    the number of choices made so far in the story
     */
    public static void cookieClicker(ArrayList<String> selections,int numChoices){
        // Creates the frame
        JFrame frame = createFrame();

        //Sets background coulur to black
        frame.getContentPane().setBackground(Color.BLACK);

        // Set the counter value and final to ensure it passes properly to the next method
        final int[] counter = {0};

        // Creates the JPanel the circle sits in
        JPanel panel = new JPanel() {
            // Overrides methods in teh superclass of JPanel to ensure stuff works - not to sure why this is hear but tutorials said it was important for later steps
            @Override
            // This allows you to create custom painting operations
            protected void paintComponent(Graphics g) {

                super.paintComponent(g); // Call superclass method to ensure proper painting
                int centerX = frame.getWidth() / 2-5; // X-coordinate of the center of the panel
                int centerY = frame.getHeight() / 2-40; // Y-coordinate of the center of the panel
                int radius = 100; // Radius of the circle

                // Gets the house your in
                String selection = selections.getFirst();

                switch (selection) {
                    case "Gryffindor":
                        g.setColor(Color.RED); // Set color to blue
                        break;
                    case "Slytherin":
                        g.setColor(Color.GREEN); // Set color to blue
                        break;
                    case "Hufflepuff":
                        g.setColor(Color.YELLOW); // Set color to blue
                        break;
                    case "Ravenclaw":
                        g.setColor(Color.BLUE); // Set color to blue
                        break;
                    default:
                        System.out.println("Unknown choice: " + selections);
                }

                // Draw the circle using fillOval method
                g.fillOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
            }
            @Override
            // This sets the new size of the panel to ensure when you add the cricle it doesn't shirnk the frame and remains the same
            public Dimension getPreferredSize() {
                return new Dimension(frame.getWidth(), frame.getHeight()); // Set preferred size of the panel
            }
        };
        panel.setBackground(Color.BLACK);

        // Set layout to BorderLayout - (North, South, East, West, Center)
        panel.setLayout(new BorderLayout());

        // This makes it so your code can check the activty of the mouse
        panel.addMouseListener(new MouseAdapter() {
            @Override
            //Wehen the mouse is clicked
            public void mouseClicked(MouseEvent e) {
                // Gets the mouses X & Y coordinate
                int clickX = e.getX();
                int clickY = e.getY();

                // Calculates the X & Y coordinates of the center of the panel
                int centerX = panel.getWidth() / 2;
                int centerY = panel.getHeight() / 2;

                // Radius of Cricle
                int radius = 100;

                // Check if the mouse click is within the circle
                if (Math.pow(clickX - centerX, 2) + Math.pow(clickY - centerY, 2) <= Math.pow(radius, 2)) {
                    // Increases the counter by 1
                    counter[0]++;
                }
            }
        });

        // Creates a button called start timer
        JButton startButton = new JButton("Start Timer");

        // Checks the activity of the button
        startButton.addActionListener(new ActionListener() {
            @Override
            // Code for what happens when clicked
            public void actionPerformed(ActionEvent e) {
                startButton.setEnabled(false); // Disable the button once clicked


                counter[0] = 0; // Ensures if circle clicked before it changes

                // Start a timer of 10 seconds
                // Specifiy the class as swing cause Timer is in two classes
                // Sets a delay in milliseconds
                javax.swing.Timer timer = new javax.swing.Timer(10000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false); // Makes frame invisble
                        startButton.setVisible(false); // Makes button invisble

                        //Shows results of the game
                        cookieClickerResults(selections, numChoices, counter);
                    }
                });
                timer.setRepeats(false); // Timer only runs once
                timer.start(); // Start the timer
            }
        });
        // Adds image to the button of the game
        JLabel imageLabel = addImageGame(frame);
        panel.add(imageLabel);

        // Add components to panel
        panel.add(startButton, BorderLayout.SOUTH);

        // Set the JPanel as the content pane of the JFrame
        frame.setContentPane(panel);

        // Pack the frame to ensure proper layout
        frame.pack();

        // Sets it to be visible
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /**
     * Creates and configures the hogwarts logo image to display in the game.
     *
     * @param frame the JFrame where the image will be displayed
     * @return imageLabel - the configured JLabel containing the image
     */
    public static JLabel addImageGame(JFrame frame) {
        // Create an ImageIcon from the image file
        ImageIcon imageIcon = new ImageIcon("clickerCookieHogwarts.png");

        // Scale it to one-third of the frame width
        Image image = imageIcon.getImage().getScaledInstance(frame.getWidth() / 3, -1, Image.SCALE_SMOOTH);

        // Create a new ImageIcon from the scaled Image
        imageIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon);
        return imageLabel;
    }
    /**
     * Displays the results of the cookie-clicker game, including the score and the prize earned based on the number of clicks.
     *
     * @param selections    the list of selections made so far in the story
     * @param numChoices    the number of choices available in the current question or action
     * @param counter       an array containing the number of clicks
     */
    public static void cookieClickerResults(ArrayList<String> selections, int numChoices, int[] counter){
        JFrame frame = createFrame();
        String prize;
        String message;

        // Determine the prize based on the counter value
        if (counter[0] < 10) {
            prize = "You don't get a cookie :(";
        } else if (counter[0] >= 10 && counter[0] < 50) {
            prize = "Plushie Cookie";
        } else {
            prize = "Owl Prize";
        }

        // Construct the message with score and prize, formats it with the counter score and prize earned!
        message = """
       Once you press start timer you have 10 seconds to click on the button as many times as you can for a prize.
       
       Score: %d clicks
       Prize: %s
    """.formatted(counter[0], prize);
        displayStory(frame, message, "outsideCommonRoom", selections, numChoices, "The-Sorting-Hat.jpeg");

        // Set default close operation to exit when the frame is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    /**
     * Displays the consequences of the player's choice in the story after choosing Truth or Dare
     *
     * @param frame         the JFrame where the story message will be displayed
     * @param selections    the list of selections made so far in the story
     * @param numChoices    the number of choices made so fare in the story
     */
    public static void story7_3_2Message(JFrame frame, ArrayList<String> selections, int numChoices) {
        String message = "";
        String selection = selections.get(numChoices);
        switch (selection) {
            case "Use cloak of invisibility":
                message = """
                      You reach for your imaginary cloak of invisibility, only to realize you left it in your other pair of invisible pants.
                       
                      But lo and behold, the door swings open mysteriously, suspicious, you regardless walk inside.
                      """;
                break;
            case "Use your knowledge of magic":
                message = """
                      You attempt to summon your inner wizard, waving your hands dramatically and muttering ancient incantations. 
                      
                      Unfortunately, you are a first year and don't know much. No sparks fly, no magic happens. However, by some miraculous twist of fate, the door creaks open.
                      """;
                break;
            case "Try to sneak in with the crowd during the morning":
                message = """
                      You blend into the bustling crowd, trying to look inconspicuous. 
                      
                      Unfortunately, your attempts at stealth are about as subtle as a herd of elephants tap-dancing in stilettos. As you fumble with the locked door, you can't open it, leaving you $5 poorer.
                      
                      As your friends laugh at you, you look at %s and dare them to break into it now.
                      """.formatted(selections.get(1));
                displayStory(frame, message, "story7_3_3_1", selections, numChoices, "The-Sorting-Hat.jpeg");

                break;
            case "Wait till 4:43 AM and use a crowbar":
                message = """
                      You meticulously plan every detail, strategizing the perfect moment to employ your trusty crowbar. First, you spend hours scouting the area, lurking in the shadows like a ninja in training. You observe the guard's patrol patterns, memorize the blind spots, and analyze the structural weaknesses of the door.

                      As the clock ticks closer to 4:43 AM, adrenaline courses through your veins. With the precision of a master thief, you approach the door, your crowbar at the ready, muscles tensed for action.
                      """;
                displayStory(frame, message, "story7_3_3_2", selections, numChoices, "The-Sorting-Hat.jpeg");
                break;
            default:
                System.out.println("Unknown choice: " + selection);
        }
        displayStory(frame, message, "question7_3_3", selections, numChoices, "The-Sorting-Hat.jpeg");
    }
    /**
     * Generates a story message describing the scene where the player meets their friends outside of their house's common room.
     *
     * @param selections    the list of selections made so far in the story
     * @param friendName2   an array containing the names of friends
     * @return              the generated story message
     */
    public static String outsideCommonFriend(ArrayList<String> selections, String[] friendName2) {
        // Setups up Random
        Random random = new Random();

        // Generate a random number 0-2
        int randomIndex = random.nextInt(3);

        // Get a random friend's name using the random index
        String randomFriendName = friendName2[randomIndex];
        String message ="";
        // Check if selections contain more than 2 elements this is to avoid indexOutOfBounds error
        if (selections.size()>2){
            // Check if the third selection is "Don’t care I have cream puffs to eat"
            if (selections.get(2).equals("Don’t care I have cream puffs to eat")){
                message = """
                    You decide to meet with your friends outside of your house's common room. %s & %s, gather outside your common room. In the midst of their intense debate about the Forbidden Book of Spells, you interject saying "There is something I need to tell you..."
                
                    You say, "I am the direct descendant of none other than Rowena Ravenclaw herself!" You begin to tell your friends about your families history with ancient spells, lost artifacts, and legendary wizardry. 
                    """.formatted(selections.get(1), selections.get(3));
            }
            else{
                // If not, use the selected friend's name and the random friend's name
                    message = """
                    You decide to meet with your friends outside of your house's common room. %s & %s, gather outside your common room. In the midst of their intense debate about the Forbidden Book of Spells, you interject saying "There is something I need to tell you..."
                
                    You say, "I am the direct descendant of none other than Rowena Ravenclaw herself!" You begin to tell your friends about your families history with ancient spells, lost artifacts, and legendary wizardry. 
                    """.formatted(selections.get(1),randomFriendName);
            }
        } else {
            message = """
                You decide to meet with your friends outside of your house's common room. %s & %s, gather outside your common room. In the midst of their intense debate about the Forbidden Book of Spells, you interject saying "There is something I need to tell you..."
                
                You say, "I am the direct descendant of none other than Rowena Ravenclaw herself!" You begin to tell your friends about your families history with ancient spells, lost artifacts, and legendary wizardry. 
                """.formatted(selections.get(1),randomFriendName);
            }
        return message;
    }
    /**
     * Determines the next steps in the story based on the player's choice. (Next Plan of Action?)
     *
     * @param frame         the JFrame to display the story
     * @param selections    the list of selections made so far in the story
     * @param numChoices    the number of choices made so far in the story
     */
    public static void story8Message(JFrame frame, ArrayList<String> selections, int numChoices)  {
        String message = "";
        String selection = selections.get(numChoices);
        switch (selection) {
            case "Go to the Grand Staircase":
                allQuestion(selections, numChoices,"grandStaircase");
                break;
            case "Go to the library":
                message = """
                           You head to the restricted section of the library and go to find the book named "“V̴̺̜̯̝͖̗̏͛͆͋̿A̷̱̍̆͋̎̕͝ͅL̴̻͂̇͗̕U̵̥͂T̵̛̫̙̃̒S̸̛͖̼̼̭̜͉̐̆͂̒̀ ̷̡̟̬̟̰̞́͂̌Ǫ̶̦̻͈͎͗̎͜F̷͚͕̭͕̲͛̏͋̒̌ͅ ̷̢̪̮̦̝̙̾̂̚Ḧ̴̻́͑̀͘Ơ̶̙͔̟͚̐̄̇͒͌G̷͚͙̓͒̄̍̐͝Ẁ̷͉̓͒̕͘Ȁ̸̼R̴̯͋͂̂T̴̳͙͚͚́̐̈́͆͌S̸̛̱̀̋̋̈́͌".
                           
                           It's said to contain the location of all the valuts and secerts in hogwarts, it should contain the location of "The Forbidden Book of Spells"
                           """;
                displayStory(frame, message, "story8_2", selections, numChoices, "The-Sorting-Hat.jpeg");
                break;
            case "Go to Class and Talk to Teacher":
                message = """
                          You need to get an adults perspective on this, logical, so you head to your spells class to talk to Mr.Flitwick. 
                          
                          He should be able to assit you in this matter, it's called "The Forbidden Book of Spells" so obviously your spells teacher is the best to ask : )
                          """;
                displayStory(frame, message, "story8_3", selections, numChoices, "The-Sorting-Hat.jpeg");
                break;
            default:
                System.out.println("Unknown choice: " + selection);
        }
    }
    /**
     * Determines the outcome of the story based on the player's choice of passcode.
     *
     * @param frame         the JFrame to display the story
     * @param selections    the list of selections made so far in the story
     * @param numChoices    the number of choices made so far in the story
     */
    public static void story9Message(JFrame frame, ArrayList<String> selections, int numChoices)  {
        String message = "";
        String selection = selections.get(numChoices);
        String[] passcode = {"Sarras Ézama - Parseltongue","Open Sesame","Alaris Alakaz - Aviaris tongue","Use your magic"};
        switch (selection) {
            case "Sarras Ézama - Parseltongue":
                message = """
                           The lock on the door seems to pulse with an ancient energy. But its not opening it seems to be getting angery!!!
                           
                           ...
                           
                           Boom!!!
                           
                           ...
                          
                           You get trapped in a cage and can't get out : (
                           """;
                displayStory(frame, message, "trappedEnding", selections, numChoices, "The-Sorting-Hat.jpeg");
                break;
            case "Open Sesame":
                message = """
                           You jokingly say "Open Sesame" like that would work... The door heard you *gulp*
                        
                          ...
                           
                          Boom!!!
                           
                          ...
                           
                          You get trapped in a cage and can't get out : (
                           """;
                displayStory(frame, message, "trappedEnding", selections, numChoices, "The-Sorting-Hat.jpeg");
                break;
            case "Alaris Alakaz - Aviaris tongue":
                message = """
                          Aviari's Tongue! - The glyphs dance before your eyes, forming a message: 'Invoke the magic of the skies, and the path shall unfold.' 
                          
                          a quiet voie says, * Will you trust in the power of the heavens to unlock the way forward? * 
                          
                          As you mutter "Alaris Alakaz" the doors begin to open and you head forward
                          """;
                displayStory(frame, message, "doorOpened", selections, numChoices, "The-Sorting-Hat.jpeg");
                break;
            case "Use your magic":
                Random random = new Random();
                int randomInt = random.nextInt(3);
                if(randomInt == 0){
                    message = """
                            Your wizard! You will use brute force to get through this! Channeling the essence of your power... wait something is happening
                        
                            You feel the magic pouring into you like you have never felt, you eyes begin to glow, your wand create a huge ball of magic
                        
                            As your about to send it towards the door, your hand feels like its going to slip, then the hand of Rowena Ravenclaw aids you to sends it straight towards the door
                        
                            BOOM!!! ITS OPEN!!!!
                            """;
                    displayStory(frame, message, "doorOpened", selections, numChoices, "The-Sorting-Hat.jpeg");
                }
                else{
                    message = """
                        Your wizard! You will use brute force to get through this! Channeling the essence of your power, you extend your hand toward the door and fire the strongest magic spell you can...
                        
                        AS A FIRST YEAR!!! WHAT ARE YOU THIKNING 
                        
                        It sends that same spell back into your wand, through which it goes through your arm and injuries your shoulder badly!
                        """;
                    displayStory(frame, message, "trappedEnding", selections, numChoices, "The-Sorting-Hat.jpeg");
                }
                break;
            default:
                System.out.println("Unknown choice: " + selection);
        }
    }
    /**
     * Determines the spells you have to use for dueling based on the player's house.
     *
     * @param selections    the list of selections made so far in the story
     * @param numChoices    the number of choices made so far in the story
     * @return duelSpells - an array of strings representing the available duel spells
     */
    public static String[] duelingSpells(ArrayList<String> selections, int numChoices) {
        String[] duelSpells = null;
        switch (selections.getFirst()) {
            case "Gryffindor":
                duelSpells = new String[]{"Stupefy", "Healing", "Expelliarmus", "Lumos"};
                break;
            case "Slytherin":
                duelSpells = new String[]{"Sectum Sempra", "Stupefy", "Healing", "Incendio"};
                break;
            case "Hufflepuff":
                duelSpells = new String[]{"Petrificus Totalus", "Healing", "Reducto", "Stupefy"};
                break;
            case "Ravenclaw":
                duelSpells = new String[]{"Aguamenti", "Healing", "Bombarda", "Sectum Sempra"};
                break;
            default:
                System.out.println("Unknown choice: " + selections.get(0));
        }

        return duelSpells;
    }
    /**
     * Handles the outcome of a spell cast during a duel.
     * The winning spells are (Expelliarmus, Sectum Sempra, Reducto)
     *
     * @param frame         the JFrame object to display the story
     * @param selections    the list of selections made so far in the story
     * @param numChoices    the number of choices available to the player
     */
    public static void resultOfSpell(JFrame frame, ArrayList<String> selections, int numChoices)  {
        String message = "";
        String selection = selections.getLast();

        switch (selection) {
            case "Stupefy":
                message = """
                        You cast Stupefy, but the villain dodges it with remarkable agility! Before you can even send off another spell, he dahses at you with furious speed. 
                        
                        ...
                        
                        (It goes all black)
                        
                        ... 
                        
                        You see the books gone and a note in your hand saying "Thank you x_x"
                        """;
                displayStory(frame, message,"endingLostDuel",selections,numChoices,"The-Sorting-Hat.jpeg");
                break;
            case "Healing":
                message = """
                        You invoke the healing charm, to try and heal your friends so they could help you defeat the villian! But before it could reach your friends, he gets in the way, you end up healing him instead!
                        
                        He says,"Thank you for that I will make sure to kill you last" in a blink you hit the ground
                        
                        ...
                        
                        (It goes all black)
                        
                        ... 
                        You see the books gone and a note in your hand a note "Thank you, keep this signed note for when the world ends, I will make your death a bit less painful then your friends - Love ḑ̴͕̗̤̣̟̬̝̲͍̻͙͖̂̑̂̽͊͆̃͂̌͊̋̕ë̴̡̤̲̲̥̠̣̳̏̉̈͐̔̌̒̏̈́̆̃̕ạ̵̡̛͙̩̭̳͎̠̬̘͚̰̱̜̙̰̖͓̌̌͆̑͋̆͗̿̈͗̌͒̀͜͝͠t̸̡̨̢̲̭̬̞̦͍̟͎͍̰̞̗͈̩͆̒̇͒͗̀̾̈́̎͐̊͠h̵̟͋̌͒̃"
                        """;
                displayStory(frame, message,"endingLostDuel",selections,numChoices,"The-Sorting-Hat.jpeg");
                break;
            case "Expelliarmus":
                message = """
                        Expelliarmus! The villain's wand flies out of their hand, giving you an advantage! It catchs the villian off guard "IMPOSSIBLE!!!" 
                        
                        You jump towards the book and reach it before him... you hear the voice of Rowena Raveclaw saying "Vimulsum Incantate" the book opens and you get a surge of magic energy that no one has ever experienced
                        
                        You blast the villian with such force that he evaporates in a blink and you go towards you friends to go heal them! 
                        """;
                displayStory(frame, message,"endingWinDuel",selections,numChoices,"The-Sorting-Hat.jpeg");
                break;
            case "Lumos":
                message = """
                        Lumos? Nope, yeah he got you so quickly that I'm just going to cue the Lost Ending... why? ఠ _ ఠ
                        """;
                displayStory(frame, message,"endingLostDuel",selections,numChoices,"The-Sorting-Hat.jpeg");
                break;
            case "Sectum Sempra":
                message = """
                        Sectum Sempra! Your curse slashes through the air, they inflict deep wounds into the villian. He falls dead in his tracks, before he wakes you jump towards the book.
                        
                        You hear the voice of Rowena Raveclaw saying "Vimulsum Incantate" the book opens and you get a surge of magic energy that no one has ever experienced 
                        
                        You grab your friends and the book and teleport out of the vault!
                        """;
                displayStory(frame, message,"endingWinDuel",selections,numChoices,"The-Sorting-Hat.jpeg");
                break;
            case "Confringo":
                message = """
                        Confringo! Your explosive spell hurtles towards your opponent, causing an explosion big enough to kill him...
                        
                        You can't belive your eyes, as you see your expolsion getting sucked into the mouth of the villian, with a grin on his face, he shoots it back at you!
                        
                        (It goes all black)
                        
                        You see the books gone and your wand broken! You also get a note "Children shouldn't be allowed wands"
                        """;
                displayStory(frame, message,"endingLostDuel",selections,numChoices,"The-Sorting-Hat.jpeg");
                break;
            case "Incendio":
                message = """
                        Incendio! Flames erupt from your wand, threatening to engulf the enitre room!
                        
                        You can't belive your eyes, as you see your flames getting sucked into the mouth of the villian, with a grin on his face, he shoots it back at you!
                        
                        (It goes all black)
                        
                        You see the books gone and your wand broken! You also get a note "Children shouldn't be allowed wands"
                        """;
                displayStory(frame, message,"endingLostDuel",selections,numChoices,"The-Sorting-Hat.jpeg");
                break;
            case "Petrificus Totalus":
                message = """
                        Petrificus Totalus! Your opponent freezes in place, rendered immobile by your spell. But he just start violently laughing and breaking out of spell, is it brute force? or shear magic ablitiy!!!
                        
                        Eitherway before you could even think of what to do next 
                        
                        (It goes all black)
                        
                        You see the books gone and a note saying "Thank you x_x" 
                        """;
                displayStory(frame, message,"endingLostDuel",selections,numChoices,"The-Sorting-Hat.jpeg");
                break;
            case "Reducto":
                message = """
                        Reducto! Your spell blasts towards him, he wasn't expecting such a powerful spell from a first year, he tried to teleport but the room glows with the power of Rowena Ravenclaw stopping him from teleporting
                        
                        Poof he is gone, reduced to dust...
                        
                        You grab your friends and the book and walk out of the vault.
                        """;
                displayStory(frame, message,"endingWinDuel",selections,numChoices,"The-Sorting-Hat.jpeg");
                break;
            case "Aguamenti":
                message = """
                        Aguamenti! A jet of water streams from your wand, to overwell him from moving... but it has no effect he instantly freezes the water coming from your wand! 
                        
                        You says, "Let me show you real magic!", he shoots a jet of water at you with such force you get knocked to the wall, he begins to fill the entire room in water almost drowing you...
                        
                        (It goes all black)
                        
                        You wake up sogging wet, when you look up you see in flames on the wall of the vault saying, "Thank you!"
                        """;
                displayStory(frame, message,"endingLostDuel",selections,numChoices,"The-Sorting-Hat.jpeg");
                break;
            case "Bombarda":
                message = """
                        Bombarda! Your explosive spell detonates, aiming to shatter your opponent's path to the book...
                        
                        You can't belive your eyes, as you see your expolsion getting sucked into the mouth of the villian, with a grin on his face, he shoots it back at you!
                        
                        (It goes all black)
                        
                        You see the books gone and your wand broken! You also get a note "Children shouldn't be allowed wands"
                        """;
                displayStory(frame, message,"endingLostDuel",selections,numChoices,"The-Sorting-Hat.jpeg");
                break;
        }
    }
    /**
     * The list of credits printed in the terminal
     */
    public static void creditsGoesTo(){

        System.out.println("""
                
                A lot of credits goes to first and foremost Ms.Fisher for giving me the starting and basic knowleadge of Java + Data Structures cause without that I wouldn't be abel to do all of thi
                Also would like to thank Youtube a lot specfically:
                
                Ever video "Alex Lee" has ever made
                https://www.youtube.com/watch?v=RrYf-ixKdTY&pp=ygUSamF2YSBzd2luZyBjcmljbGVz - How to make circles
                https://www.youtube.com/watch?v=LRzf2JChudM&pp=ygUQamF2YSBzd2luZyB0aW1lcg%3D%3D - How to make a timer
                
                and 
                
                https://www.javatpoint.com/java-swing - It had everything, hard to use at first but Alex Lee again create help
                
                It was honestly so much fun working on this, I spent defenitly to long and I have so many more things I wanted to add : ( that I didn't get to
                
                (Sorry if the images don't change my plan was to have individual images of the story but couldn't do it)
                
                Otherwise hope you enjoyed it!!!
                
                Aryan Khimani Co.
               
                """);

    }
}



