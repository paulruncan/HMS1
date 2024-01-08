# HMS1
    JavaFX project. (Hospital Management) with PostGresSQL database connection
## Public View
<h3>Menu for Users</h3>

 <h4>**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1. Emergency Room:**</h4>
 
    1.Text Fields
        -Id Field: Has no use for the user; (*)
        -Name Field: The user can use the name field to search for a patient
              and see if he's in the Emergency Room
    2. Buttons
        -Check Existence Button: After filling up the name field the user can press
              this button and the table will show the database entries that match the name
        -Update Button: The table goes back to show all the database entries corresponding
              to the Emergency Room
        -Check Price Button: shows the price for 1 dose of medicine for the pacient. I
              considered the fact that in the emergency room you get only 1 dose of medicine
              before being transfered somewhere else or released.
        -Go Back Button: takes you back to the previous page.
    
 <h4>**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2. Intensive Care Room:**</h4>
    
    1. Text Fields:
        -Id Field: (*);
        -Name Field:The user can use the name field to search for a patient
              and see if he's in the Intensive Care Room
        -Days: Can specify the number of days for paying medicine
    2. Buttons:
         -Check Existence Button: After filling up the name field the user can press
              this button and the table will show the database entries that match the name
        -Update Button: The table goes back to show all the database entries corresponding
              to the Intensive Care Room
        -Check Price Button: shows the price for 1 dose of medicine for the pacient.
        -Check Price/Days Button: shows the price for the number of days selected and the
              prospect given to the pacient
        -Go Back Button: takes you back to the previous page.

<h4>**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3. Morgue:**</h4>
    
    1. Text Fields:
        -Id Field: (*);
        -Name Field:The user can use the name field to search for a body
              and see if it is in the Morgue
    2. Buttons:
        -Check Existence Button: After filling up the name field the user can press
              this button and the table will show the database entries that match the name
        -Update Button: The table goes back to show all the database entries corresponding
              to the Morgue
        -Go Back Button: takes you back to the previous page.
<h4>**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3. Back To Login**</h4>

        Goes back to the Main Page

## Medic Login
    
        If you enter the admin user and password, you will be redirected to the admin page, else
    you'll be directed to the medic page if your account is correct

<h4>**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1. Emergency Room:**</h4>

    1.Text Fields
        -Id Field: based on the ID field we use the edit/remove functions
        -Name Field: The doctor can use the name field to add/edit data for a patient
        -Medicine Field: The doctor can use the name field to add/edit data for a patient
        -Price Field: Used to set/change price for patient's medicine
    2. Buttons
        -Add Patient Button: adds a patient to the hospital database with the data specified 
              in the text fields
        -Update Button: Updates the table to the latest version(lastest data from the database)
        -Edit Button: Edits data for the patient selected or the patient with id specified in the ID text field
        -Remove Patient: Removes from database the selected patient or the patient with id specified in the ID text field
        -Go Back Button: takes you back to the previous page.

<h4>**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2. Intensive Care Room:**</h4>

    1.Text Fields
        -Id Field: based on the ID field we use the edit/remove functions
        -Name Field: The doctor can use the name field to add/edit data for a patient
        -Medicine Field: The doctor can use the medicine field to add/edit data for a patient
        -Price Field: Used to set/change price for patient's medicine
        -Prospect Field: Should tell how a patient should take medicine(1->1/day;101->one in the
              morning and one in the evening;111->one in the morning, one in the afternoon, one in
              evening
    2. Buttons
        -Add Patient Button: adds a patient to the hospital database with the data specified 
              in the text fields, if prospect != {1,101,111} error.
        -Update Button: Updates the table to the latest version(lastest data from the database)
        -Edit Button: Edits data for the patient selected or the patient with id specified in the ID text field
        -Remove Patient: Removes from database the selected patient or the patient with id specified in the ID text field
        -Go Back Button: takes you back to the previous page.

<h4>**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3. Morgue:**</h4>

    1.Text Fields
        -Id Field: based on the ID field we use the edit/remove functions
        -Name Field: The doctor can use the name field to add/edit data for a patient
        -Organ Field: Contains the Organ that is eligible for transplant.
    2. Buttons
        -Add Patient Button: adds a patient to the hospital database with the data specified 
              in the text fields
        -Update Button: Updates the table to the latest version(lastest data from the database)
        -Edit Button: Edits data for the patient selected or the patient with id specified in the ID text field
        -Remove Patient: Removes from database the selected patient or the patient with id specified in the ID text field
        -Go Back Button: takes you back to the previous page.
        -Check Button: you can search by name of patient or by organ to see who has the organ you need for transplant;

<h4>**&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4. Back To Login:**</h4>
        
        Goes back to the medic login.

## Admin Page
    
    As an admin you can add, edit and remove a medic account from the database.

### The ID Field should never be filled if you want to add something in the system.

## Diagram
![diagrama.png](src%2Fmain%2Ffoto%2Fdiagrama.png)

# Controllers
![diagram2.png](src%2Fmain%2Ffoto%2Fdiagram2.png)

# Utils
![utils.png](src%2Fmain%2Ffoto%2Futils.png)

# Classes
![Classes.png](src%2Fmain%2Ffoto%2FClasses.png)

# Database
![database.png](src%2Fmain%2Ffoto%2Fdatabase.png)
#### Thanks for choosing HMS