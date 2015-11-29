# Iteration 1: 22-Oct - 5-Nov

##### [Oct 29 - 2015] From: SM | To: PO, PM, SPO

Good morning David! :)

We are now right in the middle of the first iteration and we would like to share what we've been doing with you.

As for now, we have already implemented the authentication system: that means users can already register and sign in to the platform!  
The database is still being developed: users can have 4 roles and this is already in the DB, but they can not edit them yet.

We are currently working on USs 1-00 and 2-01. I have also sent you an invitation to the project account in Pivotal Tracker - this is where we update the user stories, their estimates, as well as their status. Can you confirm you have access to it and that you can see what user stories we are currently working on?  
Still regarding these user stories (the ones we are currently trying to implement), do you agree that we start with them? Or would you prefer we start with others? We chose these two because we think they are a good start.

I think that's all for now! :P

Kind regards in name of the dev team,  
Henrique Ferrolho.


---

##### [Oct 29 - 2015] From: PO | To: SM, PM, SPO

Hi Henrique,  
I have access to pivotal tracker, thanks. Those two user stories are a great start.  
Thanks for the update.

Kind regards,  
David


---

##### [Nov 14 - 2015] From: SM | To: PO, PM, SPO

Hello David! :)

We have setup an host for you to see how the product is going, you can access it here: http://ferrolho.xyz  
In Pivotal Tracker, we have delivered User Stories 0-00, 0-01, and 1-00. Can you please go to the link above, test those US and accept them in Pivotal Tracker? If you have any doubt please tell me right away!

Regards,  
Henrique Ferrolho.


---

##### [Nov 15 - 2015] From: PO | To: SM, PM, SPO

Hi Henrique,

Thanks for the link. I'll go through it tomorrow morning and see if another member of my group can check it too.

Kind regards,  
David


---

##### [Nov 17 - 2015] From: SM | To: PO, PM, SPO

Good night David! :)

I am still waiting for your feedback about the USs we have deployed to the hosting server. Did your meeting go well today morning? :) What did the group say about the site so far?

I bring good news! Meanwhile, we have already finished and deployed one more US: 1-01. You can test it and if you think it is ok you can press "accept" in pivotal tracker... :) This is important for us to keep it clean and organised.

Here is a screenshot of what you should be able to see in the supply page:  
*screenshot omitted *

Looking forward to hear from you,  
Henrique Ferrolho.


---

##### [Nov 17 - 2015] From: PO | To: SM, PM, SPO

Thanks Henrique,  
I was busy writing a response when your email came earlier. I had to sign up again since the previous login credentials didn't work after your update.  
Sign up: No Problem. Were we not going to have more fields like country etc?  
Supply: Will the items donated be able to be categorised at some point? Wondering if the free text form field will make it difficult to sort or search food later. Your thoughts??  
Supply: Can there be a label on the amount field so one knows what they are inputting? My first thought was "should I put the number of oranges or the weight."  
Supply: If one presses "enter" without filling in something then all the items on the page disappear. They reappear when an item is input.  
supply: Can one edit or cancel an added resource? Is that still coming?

In pivotal tracker when I clicked on "accept" for 0-00 nothing happened.

The others haven't seen anything on the site yet. Hoping to get them all together or at least some of them. It didn't work out this morning to meet with them. I'll keep trying to get them together.

Chat soon.  
David


---

##### [Nov 17 - 2015] From: SM | To: PO, PM, SPO

Good morning David :)

Every time we deploy a new feature we reset the DB - we are still testing, there's no harm doing this, and it assures us that there are no conflicts. The only down side is that you will have to sign up every time we deploy a new version, at least for now.

Sign up  
In the first mock-ups I've sent you, the sign up form also had the following fields: birth date, country, and city. We did not include them for now just for simplicity! Do you want us to add these to the current form?

Supply  
Categories: we did not think of this. If you would like to have a category associated with a supply offer, please elaborate a list of categories for us to make a drop down so the user can choose one of them when submitting a supply offer.  
Label: what kind of label are we talking about? Kg, L, quantity? Please elaborate a list for this as well. This is suitable for another drop down list next to the amount field.  
Empty field bug: I will look into this and solve it asap.  
Edit/cancel: Yes, this will be possible. You can check this US in pivotal tracker, it is US 1-02. It is still coming.

I guess I should be the one accepting/rejecting USs in pivotal tracker. I have accepted USs 0-00, 0-01, and 1-01. I have rejected US 1-00 because of the category and unit labels issue that needs to be solved.

Looking forward to know your feedback about these matters,  
Henrique Ferrolho.


---

##### [Nov 17 - 2015] From: PO | To: SM, PM, SPO

Hi Henrique,  
Your points are noted. Here are my responses

Sign up:  I think at least country and city should be added since geography is the main differentiator. Area/ suburb can be added later?? People searching for things need local results returned so that need to be a backbone of the searching/ availability functionality.

Supply:  
Categories: I like the drop down list idea. I assume extra categories will be able to be added by administrators later on?? You can start with the following list  
    --------  
Breads  
Vegetables  
Meat  
Sea Food  
Dairy  
Grains (pasta, rice etc)  
Fruits  
Condiments (sauces ,pesto etc)  
Drinkables  
Miscellaneous (Chips, biscuits, cooking oils, etc)  
    -------------

Labels: Yes to drop-down too! Will admin be able to add more labels/ descriptors too?  
    -----  
Quantity  
Kg  
Pieces  
Packets  
Cans  
Boxes  
Bottles  
Crates  
    ------

Kind regards,  
David


---

##### [Nov 19 - 2015] From: SM | To: PO, PM, SPO

Hello David! :)

Sorry I did not reply to your email right away. I wanted the team to address these issues and deploy the fixes asap for you to give more feedback. And we managed to do so! :)

We have already deployed to http://ferrolho.xyz/ the new features:  
- The bug you mentioned of the supplies list turning empty if the user submitted a form with an empty field/invalid amount has been solved  
- Supplies have a category and an amount label  
- Supplies can now be edited/deleted

And to answer both your questions regarding the dropdown lists: yes, the admin will be able to edit these through the Back Office in the future.

We still need to add the country field in the sign up form though... I think that for now, specifying the country is enough. Is it ok for you?

And that's it! :) Today marks the end of iteration 2. I think we have made some significant progress.

Please tell me what you think of the new features/fixes, and I'll keep you up to date with our progress.

Kind regards,  
Henrique Ferrolho.


---

##### [Nov 25 - 2015] From: PO | To: SM, PM, SPO

Hi Henrique,

Really sorry for the delayed response. I thought I had responded already.

Where are you suggesting that they specify country?

Can the Label in the first field be "Description" instead of Resource?

Is the edit button to save the edit or to make the edit? One can edit without clicking edit first?

I like the beginnings of the sorting centre acceptance page. The items just vanish when accepted but i'm sure you are working on where they appear again ;-)

Thanks so much.


---

##### [Nov 25 - 2015] From: SM | To: PO, PM, SPO

Good night David! :)

I suggest that the country should be specified at sign up like you suggested. We just didn't have the time to add it yet.

Sure, totally! I am going to change it to "Description".

The edit button updates the entry with the value in the form fields. If the user does not change anything and clicks edit, nothing happens. If he changes the name field (for example) and clicks edit, then the name of that entry is updated.

Correct! The currently deployed version at ferrolho.xyz is outdated. When the user accepts a supply from a sorting center, it will vanish from that listing just like it is doing right now, but it will appear on another listing called "incoming resources". Actually, this is already working and you will get the chance to try it out when we deploy the new features.

Do you accept US 1-00, 1-02, and 2-01? (Check pivotal tracker for details)

I wanted to mention you one last thing in this email: we are currently in the middle of the penultimate sprint. There is only one sprint left besides the one we are currently on. So that leaves us with 3 weeks remaining. I don't think we will manage to finish the project on time, but we will do our best with the time we've got!  
Do you have any preference regarding the order of implementation of the USs?

Kind regards,  
Henrique Ferrolho.


---

##### [Nov 28 - 2015] From: SM | To: PO, PM, SPO

Good morning David!

We have just deployed a new version of the platform where users need to specify their country in the sign up form. The first supply label has also been changed to "Description" just like you requested.

The team would also appreciate if you could find the time to check Pivotal Tracker and tell us if we can accept the USs that are yet to be accepted/rejected.

Again, we are close to the end of the goal of our course. We are short in time to implement the entire platform, so if you want us to prioritize something, now is the time to make those decisions. :)

Looking forward to hear from you asap,  
Henrique Ferrolho.


---

##### [Nov 29 - 2015] From: PO | To: SM, PM, SPO

Hi Henrique,

The platform is working nicely with the current functionality, it is a pity you will not be able to see it through to full completion. I have been thinking about the steps to take in order to get this to a usable platform after you complete your work. Please would you (before you get to the end) list the coding skills/ language and platform resources required to take this platform further. That way I can start looking for funding for resources to see this thing through. Currently we have a huge refugee problem here and this mvp, if usable, could really really help out.

With regards to the current platform, the key thing that is missing is the geography element as I mentioned earlier. It needs to be there on a granular level otherwise the linking of supplies to sorting centres or requesters becomes irrelevant. Country is great but that is still too broad to get any use of the mvp.  I will gladly forfeit all the transporter user stories to have that implemented before your finish.

So my priorities are as follows:  
->Location (province city + suburb)  for suppliers, sorting centres + requesters profiles. Should be added on the profile page along with telephone number.  
-> US 1-00 : add suburb/city + time stamp under each item supplied. Eg. (Malmö, Central ; added 20min ago) OR ( Malmö, Central ; added 2015-11-29 , 13h45).  If the space is a problem then perhaps have a little icon that one can hover over to see this info as a tooltip /label.  
-> US 2-03 (Sorting center check in incoming resources)  
->Requester (US 3-0x)  Ability to search , description, category, country, province , city and suburb. ( the default locations for searches can match users profile settings.)  
->Admin console (US-5-00)

Please let me know your thoughts on this so we can find the best way to getting a basic usable supply and demand linking system before you end. I'll try and respond faster so you can maintain momentum.

Thanks for all your work so far.  
Kind regards,  
David
