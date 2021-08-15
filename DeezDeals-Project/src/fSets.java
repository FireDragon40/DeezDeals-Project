
import twitter4j.*;


/**
 * This class allows us to create an object that holds our parameters used to filter through the tweets.
 * @author Deez Deals
 */ 
public class fSets 
{

    /**
     * True if there is a keyword search, false if not.
     */
    private boolean keyWordOn;

    /**
     * If search is using a keyword, that keyword is stored here. 
     */
    private String keyWord;
    
    /**
     * True if field for newest is set, false if not.
     */
    private boolean food;

    /**
     * True if field for cheapest price is set, false if not.
     */
    private boolean clothes;

    /**
     * True if field for newest is set, false if not.
     */
    private boolean other;

    /**
     * Basic contrsutor that sets all of the paramaters to false.
     */
    public fSets()
    {
        this.keyWordOn = false;
        this.food = false;
        this.clothes = false;
        this.other = false;
        this.keyWord = "";
    }
    
    /**
     * Takes in true or false for each paramater to know what user checked boxed on the page.
     * @param kwOn True if there is a keyowrd in the search bar, false if no keyword.
     * @param fd True if there is a checkmark in the food box on the website.
     * @param clths True if there is a checkmark in the clothes box on the website.
     * @param oth True if there is a checkmark in the other box on the website.
     */
    public fSets(boolean kwOn, boolean fd, boolean clths, boolean oth)
    {
        this.keyWordOn = kwOn;
        this.food = fd;
        this.clothes = clths;
        this.other = oth;
        this.setOther();
        this.keyWord = "";
    }

    /**
     * Returns whether or not the food parameter is on or off (True is on false is off).
     * @return The boolean value of the food parameter.
     */
    public boolean getFood()
    {
        return this.food;
    }

    /**
     * Returns whether or not the clothes parameter is on or off (True is on false is off).
     * @return The boolean value of the sort by clothes deal parameter.
     */
    public boolean getClothes()
    {
        return this.clothes;
    }

    /**
     * Return the boolean value of whether the keyowrd field is activated (true is on false is off).
     * @return The boolean of the keyWordOn field.
     */
    public boolean getKeyWordOn() 
    {
        return this.keyWordOn;
    }
    
    /**
     * Returns the keyword that was passed in to the fieldSet class.
     * @return The string that will be used for the keyWord.
     */
    public String getkeyWord()
    {
        return this.keyWord;
    }

    /**
     * Return the boolean value of whether the other is activated (true is on false is off).
     * @return
     */
    public boolean getOther() 
    {
        return this.other;
    }

    /**
     * Takes in a string that will be used for the keyWord search.
     * @param word The keyword that will be used to filter.
     */
    public void setKeyword(String word)
    { 
        if(word != null && !word.trim().isEmpty())
        {
            this.keyWord = word;
            this.keyWordOn = true;
        }
        else
        {
            this.keyWord = "";
            this.keyWordOn = false;
        }
    }

    /**
     * Makes the food and clothes paramaters false as other acts as an alternative to both.
     */
    private void setOther()
    {
        if (this.getOther())
        {
            this.clothes = false;
            this.food = false;
        }
    }

    

}
