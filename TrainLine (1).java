public class TrainLine {

    /** The name of the trainline */
    private String name;
    /** Points to the first station in the trainline */
    private TrainStation head;
    /** Points to the last station in the trainline */
    private TrainStation tail;
    /** Keeps a running tally of train stations in the trainline */
    private int numberOfStations;

    /** Full constructor */
    public TrainLine(String name, TrainStation head) {
        this.name = name;
        this.head = head;
        this.numberOfStations = 0;
        if (this.head != null) {
            // If head is not null, there is one station in the line
            this.numberOfStations = 1;
        }
        // At initialization head and tail point to the same station even if null
        this.tail = null;
    } // full constructor

    /** Basic constructor */
    public TrainLine(String name) {
        this(name, null);
    } // basic constructor

    /**
     * Creates a new station with the given name and adds it to the end of the line.
     */
    public void add(String name) {
        // Create the new station to add
        TrainStation newStation = new TrainStation(name);
        // Determine where to place the new station
        if (this.head == null) {
            // Trainline is empty, make new station the head of the line
            this.head = newStation;
        } else {
            // When there is a head station already, add the new station after the last
            // station in the line.
            this.tail.setNext(newStation);
            newStation.setPrevious(this.tail); 
            //added to set for reverseList
        }
        // The new station becomes the tail station of the line
        this.tail = newStation;
        // Update station count
        this.numberOfStations++;
    } // method add

    /** Returns the number of stations in the line >= 0 */
    public int getNumberOfStations() {
        return numberOfStations;
    } // method getNumberOfStations

    /*
     * METHOD STUBS TO ENSURE CODE COMPILES. YOU WILL HAVE TO REWRITE THIS CODE TO
     * MATCH THE SPECIFICATIONS AND ALSO YOU'LL HAVE TO WRITE METHOD isEmpty.
     */
    public boolean contains(String name) {
        //doesContain is meant to return value
        boolean doesContain = false; 
        //start with the head
        TrainStation current = this.head;
        //while loop to run until current meets null(the end)
        while (current != null) {
            //if loop to make sure name of trainstation matches with an existing one
            if (current.getName().equals(name)) {
                //true if trainstation exist
                doesContain = true;
            }
             //moves on to the next station to view if its there
            current = current.getNext();
        }
        // retursn true or false
        return doesContain;
    }

    public int indexOf(String name) {
        //starts off in the head of the station.
        TrainStation current = this.head;
        //index start of the trainstation order
        int index = 0;
        int positions=-1;
        //while loop to count each trainstation position
        while (current != null) {
            //gets position of the trainstation
            if (current.getName().equals(name)) {
                //returns position
                positions =index;
            }
            //moves on to the next trainstation
            current = current.getNext();
            //adds position number
            index++;
        }
        //returns position
        return positions;
    }

    public String reverseList() {
        String reversedList = "";
        //starts from the last station
        TrainStation current = this.tail;
        //if the trainstation is not null 
        while (current != null)  {
            //starts brom last line & gets name
            reversedList+= current.getName() +"\n";
            //moves on to the next station backwards
            current= current.getPrevious();
        }
        //sents back the list backwards
        return reversedList;
    }
    public boolean isEmpty() {
        // found null 
        return this.head == null;
    }
    

    /*******************************************************************************
     * DO NOT REMOVE TESTS FROM THE CODE BELOW. YOU MAY **ADD** YOUR OWN TESTS BUT *
     * YOU MAY NOT REMOVE ANY OF THE EXISTING TEST CODE. *
     ******************************************************************************/
    public static void main(String[] args) {
        // A few station names
        String[] stationNames = { "Howard", "Jarvis", "Morse",
                "Loyola", "Granville", "Thorndale" };
        // A populated trainline
        TrainLine redLineSB = new TrainLine("Red Line SB");
        for (String station : stationNames) {
            redLineSB.add(station);
        }
        // An empty trainline
        TrainLine brownLineSB = new TrainLine("Brown Line SB");
        // A random station name
        String randomName = "Oak Park";
        // Guard tests
        redLineSB.indexOf(null);
        redLineSB.contains(null);
        // Test indexOf on existing values
        boolean indexOfTestExisting = true;
        for (int i = 0; i < stationNames.length; i++) {
            indexOfTestExisting = (indexOfTestExisting && (redLineSB.indexOf(stationNames[i]) == i));
        }
        // Test indexOf for non existing station
        boolean indexOfTestNotExisting = (redLineSB.indexOf(randomName) == -1);
        // Test indexOf on empty line
        boolean indexOfTestingEmpty = (brownLineSB.indexOf(stationNames[0]) == -1);
        // Test contains for existing stations
        boolean containsTestExisting = true;
        for (String station : stationNames) {
            containsTestExisting = (containsTestExisting && redLineSB.contains(station));
        }
        // Test contains for non existing values
        boolean containsTestNonExisting = (!redLineSB.contains(randomName));
        // Test reverse list
        String expectedReverseList = "";
        for (int i = stationNames.length - 1; i >= 0; i--) {
            expectedReverseList = expectedReverseList + stationNames[i] + "\n";
        }
        boolean reverseListTest = redLineSB.reverseList().equals(expectedReverseList);
        // Reporting strings
        final String PASS = "Pass";
        final String FAIL = "Fail";
        String reportIndexOfTestExisting = (indexOfTestExisting) ? PASS : FAIL;
        String formatIndexOfTestExisting = "\n\nindexOf test for existing values: ......... %s";
        String reportIndexOfTestNonExisting = (indexOfTestNotExisting) ? PASS : FAIL;
        String formatIndexOfTestNonExisting = "\nindexOf test for non existing values: ..... %s";
        String reportIndexOfTestEmpty = (indexOfTestingEmpty) ? PASS : FAIL;
        String formatIndexOfTestEmpty = "\nindexOf test for empty object: ............ %s";
        String reportContaisTestExisting = (containsTestExisting) ? PASS : FAIL;
        String formatContainsTestExisting = "\ncontains test for existing values: ........ %s";
        String reportContainsTestNonExisting = (containsTestNonExisting) ? PASS : FAIL;
        String formatContainsTestNonExisting = "\ncontains test for non existing values: .... %s";
        String reportReverseListTest = (reverseListTest) ? PASS : FAIL;
        String formatReverseListTest = "\nreverseList test: ......................... %s\n\n";
        System.out.printf(formatIndexOfTestExisting, reportIndexOfTestExisting);
        System.out.printf(formatIndexOfTestEmpty, reportIndexOfTestEmpty);
        System.out.printf(formatIndexOfTestNonExisting, reportIndexOfTestNonExisting);
        System.out.printf(formatContainsTestExisting, reportContaisTestExisting);
        System.out.printf(formatContainsTestNonExisting, reportContainsTestNonExisting);
        System.out.printf(formatReverseListTest, reportReverseListTest);
        // ----------- YOU MAY ADD YOUR OWN TESTS BELOW THIS COMMENT LINE ---------------
    } // method main
} // class TrainLine