package by.epam.learn.daryatarasevich.barback.command;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    HELP{
        {
            this.command=new HelpCommand();
        }
    },
    LOCALE{
        {
            this.command=new LocaleCommand();
        }
    },
    COCKTAILPAGE{
        {
            this.command=new CocktailPageCommand();
        }
    },
    LISTOFCOCKTAILSTOAPPROVE{
        {
            this.command=new ListOfCocktailsToApproveCommand();
        }
    },
    HELPED{
        {
            this.command=new HelpedCommand();
        }
    },
    RATECOCKTAIL{
        {
            this.command=new RateCocktailCommand();
        }
    },
    LISTOFBARTENDERS{
        {
            this.command=new ListOfBartendersCommand();
        }
    },
    WHONEEDSHELP{
        {
            this.command=new WhoNeedsHelpCommand();
        }
    },
    LISTOFCOCKTAILS{
        {
            this.command=new ListOfCocktailsCommand();
        }
    },
    LISTOFCREATEDCOCKTAILS{
        {
            this.command=new ListOfCreatedCocktailsCommand();
        }
    },
    LISTOFINGREDIENTS{
        {
            this.command=new ListOfIngredientsCommand();
        }
    },
    UPDATEINGREDIENTFORM{
        {
            this.command=new UpdateIngredientFormCommand();
        }
    },
    UPDATECOCKTAILFORM{
        {
            this.command=new UpdateCocktailFormCommand();
        }
    },
    UPDATEUSERFORM{
        {
            this.command=new UpdateUserFormCommand();
        }
    },
    UPDATEBARTENDERFORM{
        {
            this.command=new UpdateBartenderFormCommand();
        }
    },
    LISTOFUSERS{
        {
            this.command=new ListOfUsersCommand();
        }
    },
    LISTOFSUGGESTEDCOCKTAILS{
        {
            this.command=new ListOfSuggestedCocktailsCommand();
        }
    },
    SUGGESTCOCKTAIL{
        {
            this.command=new SuggestCocktailCommand();
        }
    },
    CREATECOCKTAIL{
        {
            this.command=new CreateCocktailCommand();
        }
    },
    DELETEACCOUNT{
        {
            this.command=new DeleteAccountCommand();
        }
    },
    CHANGESTATUS{
        {
            this.command=new ChangeStatusCommand();
        }
    },
    USERSTOCHANGESTATUS{
        {
            this.command=new UsersToChangeStatusCommand();
        }
    },
    REGISTER{
        {
            this.command=new RegisterCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}

