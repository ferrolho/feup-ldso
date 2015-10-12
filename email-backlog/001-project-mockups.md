# Project mock-ups

##### [Oct 8 - 2015] From: SM | To: PO, PM, SPO

Hello David!

The team has already started working after our call this morning. We have sketched the web app platform mock-ups, a "map" of the views, and the possible navigation from and to each of them as well.  
We took a picture of it and I have attached it to this email so you can take a look at it and tell us what you think about it. Did we miss something? Is it a good start? Or is it nothing like you envisioned it?

Let me just add here a quick reminder to keep in mind from now on until the rest of our collaboration: we are not designers, we are developers. That does not mean we won't give our best to make the platform look as good as possible, but it does mean however that our main focus is to assure everything is working as it should, and leave the appearance as a secondary task. So please bear in mind that when you look at the attached photo... :P we did our best designing the mock-ups!


"So... what happens now?" - you might be asking.

Well, now the team will elaborate a list of User Stories (US) - that is a list of tasks that are necessary to complete the project - and we will assign each US a certain number of points - a cost actually (it is not a monetary cost, it is the effort we think it will take to complete the US) - that being said, if one user story is harder than another, it will have a greater cost.

We can only fulfill a certain amount of points each week, and because of that - after we elaborate the list and assign each US an amount of points - you and me will have to make another hangouts call in order for me to understand which of the tasks have a higher priority. We will start implementing the higher ones, and only then move to the others, and so on and so on.

I hope this was not too confusing... But if you did not understand something please ask me and I will try to explain it again! :)


Looking forward to hear from you! And please give me some feedback about the mock-ups attached.

In behalf of the development team,  
Henrique Ferrolho

---

##### [Oct 9 - 2015] From: PO | To: SM, PM, SPO

Hi Henrique,  
This is a great start! It makes it clearer and easier to explain.  
These are my thoughts on the flow:

Register: Option to pick category they are registering for( ie. transporter, supplier, requester (buyer), sorting centre)  
Request: possibility to add payment fee for delivery? points or money. (this is where we envision will generate money to support the system)  
Supply: Add date and time that it can be collected ( from xx:00 to yy:00)  
Supply: possibility to take picture of the products( on mobile app only)  
Transport: Ability for transporter to book all or part of the delivery and to which sorting centre they will take it.

Those are my only thoughts on the diagram so far.  
As far as design goes, I am with you on focusing on functionality. Layout and UX can come second.

Chat soon,  
David


---

##### [Oct 9 - 2015] From: SM | To: PO, PM, SPO

Hello David!

Here are my questions regarding your last response:

**Register**  
I understood that one could register on the project without having to specify one of the four actions. That one could login and only then choose what to do, because today I might want to help by transporting, but tomorrow I might want to supply resources. If I (as a user of the web app) - have to choose a category upon registry, then I would have to create multiple accounts to do different things. What do you think?

**Request**  
Can you please elaborate a bit more? Do you mean that the user who creates a request is the one who decides how many points (or money) the transporter should get after the delivery?

**Supply**  
*Date and time*: got it! No questions about this one. :)  
*Mobile app*: we do not think it is necessary to have a native web app for - let's say - android, or iOS. We are going to develop a responsive web app - that means the web pages will adapt according to the screen size where they are being displayed, changing the layout most of the times to get a "mobile feel".  
So if I am a user of the platform, and I want to access it via my android smartphone, I can use whatever browser I have installed (chrome, for example) to do so. Is this ok for you?  
This way it is also possible for the user of the smartphone to upload pictures from the phone gallery, or to just take a picture in that exact moment using the smartphone's camera.

**Transport**  
Got this one as well! :)

Kind regards,  
Ferrolho.


---

##### [Oct 9 - 2015] From: PO | To: SM, PM, SPO

Hi Henrique,

**Register**: I agree with you. Would just be nice to have some sort of way or knowing how many of each type (for analytics)  we have in a area or have some way to message just that group in the future. Your suggestions are welcome.

**Request**: No the user will not decide. I guess we could make it a function of the distance to travel and the amount of stock being transported.

**Supply**: Responsive web app is fine. Ease of use to get the image into the platform is the priority. Which ever way would be easiest for users.

Kind regards,  
David


---

##### [Oct 9 - 2015] From: PM | To: SM

Henrique, só para ti:

> **Register**: I agree with you. Would just be nice to have some sort of way or knowing how many of each type (for analytics)  we have in a area or have some way to message just that group in the future. Your suggestions are welcome.

Que tal “roles”? I.e., um utilizador ter múltiplos roles e poder escolher gual deles está activo ao longo do tempo (do ponto de vista de UX, uma lista de checkboxes resolve o problema).


---

##### [Oct 9 - 2015] From: SM | To: PO, PM, SPO

Hello again David ! :)

**Register**  
How about introducing "Roles"? This is how it would work: I register on the platform and I get to choose what I would like to do. Let's say that for now I would like to try the Transport role - I would then become part of the Transporters. Now, imagine that for some reason I don't want to be available as a Transporter - I can just go to the Roles Dashboard and deactivate the Transporter role - or I could just leave it there and activate the Supply Role to have multiple roles activated. What do you think?

**Request**  
Ok! That's exactly what I was going to suggest: the greater the amount of the cargo, and the greater the distance to travel - the greater the points rewarded should be. :)

**Supply**  
Got it!

Regards,  
Ferrolho.


---

##### [Oct 10 - 2015] From: PO | To: SM, PM, SPO

Hi Henrique,

**Register**: Ok. So the user has free access to changing the role in their own profile?  
Admin can add new roles that are exact duplicate logic flow of existing roles? If that Admin functionality part is too much for the mvp then program with that in mind for a future iteration.

**Request**:  Please document the location of where the Super User can change this points calculation.

On all system operations please put the session save timer /draft copy save timer, to a very low (frequent) value. Since it is a responsive web app and not a native app, we don't want the users losing what they had started doing because of internet access loss that causes session timeouts.  If there is a session timeout for some reason, one must be able to resume their session at the point of disconnection in their workflow, or at least as close to it as possible.

Kind regards,  
David


---

##### [Oct 10 - 2015] From: SM | To: PO, PM, SPO

Good morning David,

**Register**  
Yes, I think the user should be able to change how he wants to help by activating/deactivating roles. I might want to help as a Transporter during the major part of the year, but I might want to help as a Sorting Center - in addition to being a Transporter - during summer because I have more spare time. This is just a suggestion though, you are the one who has the final word.

"Admin can add new roles that are exact duplicate logic flow of existing roles?" - I am not sure what you mean by this, can you elaborate a bit more please? :)

**Request**  
Sure, will do!

Regarding the session data save frequency: can we keep this in mind for now, and bring it back a bit further when we start to have a more solid platform? I think we should postpone this for now and bring it back in a future iteration. What do you say?


By the way, let me thank you for your availability and promptness replying to my emails. It has been a great help for us, and it helps to keep the development momentum! :)

Have a great day!


---

##### [Oct 11 - 2015] From: PO | To: SM, PM, SPO

Hi Henrique,

Register  
I agree with you re roles in the user's profile. Go for it.  
For the sake of keeping people active it would be nice to have a way to communicate with the different roles in a location regarding news, rewards, incentives etc. Someone who was active in a role but has deactivated that role would probably still benefit from receiving such emails/ newsletters. Not sure how to beat achieve this communication part.  
.....

"Admin can add new roles that are exact duplicate logic flow of existing roles?" :  
You can ignore this point. My thought was that if we needed to add a new role to the system it would be good to have a copy function that duplicates one of the existing roles but this is not important for an mvp so don't worry about it.

Session save frequency:  
No problem. Leave until later but don't forget about it ;-)  
Is there a better way to achieve the retention of workflow than using the session save frequency? I'm open to suggestions that would reduce the server overhead.

Have a great day.


---

##### [Oct 11 - 2015] From: SM | To: PO, PM, SPO

Hello David,

**Register**  
Regarding the messaging system: I think the Admin can be able to send a message to all the people from a certain city/country with a given role. That message would be sent by email, since people Sign Up with an email. What do you think?  
About people who are no longer part of a role, they should receive those emails?

**Session save**  
We will have to do a little research in order to tell if there's a better way.

I already have everything we need to our next call! :) So let me know you're availability!

Regards,  
Henrique Ferrolho.


---

##### [Oct 12 - 2015] From: PO | To: SM, PM, SPO

Hi Henrique,  
I can meet today at your 11am or tomorrow at your 10am or 11 am.

Register:  
Email is fine. Will the personalised email broadcasts /newsletters be sent by the system or need to be downloaded /integrated with a service like Aweber or mailchimp? We can discuss this on the call as well as the topic about those no longer in a role.

Kind regards,  
David


---

##### [Oct 12 - 2015] From: SM | To: PO, PM, SPO

Hello,

I can meet at my 11am. :) I'll call you by that time.  
The goal for today's call is to go through the User Stories list we've come up with, and make sure each and every single one of them is clear. After that I will clarify some questions the team has.

I will have to talk to the team in order to decide which solution fits best regarding the email service. Since it will not be possible to meet with the entire team before our call, I would ask you to leave this for a future iteration.  
We can however brainstorm about the mechanics of users who no longer play a role and would benefit from receiving these emails in today's call. :)

Will call you soon,  
Henrique Ferrolho.


---

##### [Oct 12 - 2015] From: PO | To: SM, PM, SPO

Hi Henrique,  
I have updated the user stories doc. I tried to put the items that were essential to the mvp as high, so there are quite a few of those. There were not many that I could think of as low priority.

Chat soon,  
David
