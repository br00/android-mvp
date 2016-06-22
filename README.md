### Another example about MVP pattern? 0.0
I know, lately coders love MVP as much as hipsters love pants with cuff.
Since there are so many MVP examples online, which is both a good and a bad thing, I decided to share my version, that hopefully will be helpful to speed up the process for who have never used it.

#### So what is the MVP?
MVP (Model View Presenter) pattern is a derivative from the well known MVC (Model View Controller), which for a while now is gaining importance in the development of Android applications. There isn't the right way to implement it, every example of this pattern is different from each other, so this is the first problem of mvp: too much different examples and no documentation from google.

The MVP pattern allows separate the presentation layer from the logic, so that everything about how the interface works is separated from how we represent it on screen. 
MVP is not an architectural pattern, it’s only responsible for the presentation layer. To understand how works the mvp you can take a look to the [Clean Architecture before.](http://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html)

#### Why use MVP?
In Android we have a problem arising from the fact that Android activities are closely coupled to both interface and data access mechanisms. We can find extreme examples such as CursorAdapter, which mix adapters, which are part of the view, with cursors, something that should be relegated to the depths of data access layer. For an application to be easily extensible and maintainable we need to define well separated layers. MVP makes views independent from our data source. We divide the application into at least three different layers, which let us test them independently. With MVP we are able to take most of logic out from the activities so that we can test it without using instrumentation tests.


### How to implement MVP for Android
There are many variations of MVP and everyone can adjust the pattern idea to their needs and the way they feel more comfortable. The pattern varies **depending basically on the amount of responsibilities that we delegate to the presenter**.
Is the view responsible to enable or disable a progress bar, or should it be done by the presenter? And who decides which actions should be shown in the Action Bar? That’s where the tough decisions begin. An other difficult is that in Android we have the xml file to create the layout of view. So, we have the class java View and xml to create the layout (view). How can I use the class View if we create the layouts using xml?

![Alt text](https://s31.postimg.org/74sdnobob/mvp.png "mvp")

#### The presenter
The presenter is responsible to act as the middle man between view and model. It retrieves data from the model and returns it formatted to the view. But unlike the typical MVC, it also decides what happens when you interact with the view.
* Middle man between the Model and View
* It has a reference to the View and Model
* It introduces a level of abstraction to the data from Model and formats it before sending it to the View -> This makes the View and Model independent
* Update the UI 

#### The View
The view, usually implemented by an Activity (it may be a Fragment, a View… depending on how the app is structured), will contain a reference to the presenter. It will be responsible for creating the presenter object. The only thing that the view will do is calling a method from the presenter every time there is an interface action (a button click for example).
* It can be an Activity, Fragment, View..
* It has a reference to the Presenter
* It propagates the events from the UI to the presenter (onClick, etc)
* It exposes the methods that control the presentation of data (show/hide the loading layout)

#### The model (or Interactor)
In an application with a good layered architecture, this model would only be the gateway to the domain layer or business logic. If we were using the clean architecture, the model would probably be an interactor that implements a use case. For now, it is enough to see it as the provider of the data we want to display in the view.
* It's the gateway toward the business logic
* It contains the methods for data retrieval

# VPI
So the only difference with the MVP is the Interactor instead of the Model. For me has more sense that the presenter is connected to an object that is more than a simple Model.
The Interactor should be an object capable to create an Asynctask or a database connection, so a bridge between the presentation layer and the data layer (Asynctask, Database, Model, etc).
So it's time to call the pattern VPI and not MVP!

## Example
It's a simple app with a fake authentication with possibility to do a login and logout. 

![Alt text](https://s31.postimg.org/bhkhzi0gb/vpi01.png "login")
![Alt text](https://s32.postimg.org/fpijaf0j9/vpi02.png "main")

# Suggestions
Start to create the main Interface and write all methods thinking to the flow view -> presenter -> interactor and vice versa. 
E.G. in my ILoginVPI interface:

```java
public interface ILoginVPI {

    /**
     * Presenter operations available from the View
     *      View -> Presenter
     */
    interface PresenterActions{
        void onCreate();
        void onResume();
        void onStop();
        void onDestroy();
        void requestAuthentication(String typedPassword);
        void requestLogout();
    }

    /**
     * Interactor operations available from the Presenter
     *      Presenter -> Interactor
     */
    interface InteractorActions {
        void authenticate(String typedPassword);
        void enrollNewUser();
        void resetPassword();
        void logout();
        void checkStage();
        void onDestroy();
    }

    /**
     * Presenter operations available from the Interactor
     *      Interactor -> Presenter
     */
    interface RequiredPresenterActions {
        void onAuthenticated();
        void onPasswordResetted();
        void onUpdate(LoginInteractor.Stage aStage);
        void onError(String errorMsg);
    }

    /**
     * View operations available from the Presenter
     *      Presenter -> View
     */
    interface RequiredViewActions {
        void goToHome();
        void updateUI(LoginInteractor.Stage aStage);
        void showError(String msg);
    }

}
```


Write code in MVP,VPI, etc is harder than write the normal code. You have tree times more methods and the beginning could be very slow, but the maintainability and the fun have no price.
So happy coding;


#### Ref
* http://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/
* http://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html
* http://stackoverflow.com/questions/2056/what-are-mvp-and-mvc-and-what-is-the-difference?rq=1
* http://www.meeting.edu.cn/meeting/UploadPapers/1282707447171.pdf
