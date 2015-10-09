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

```
**Register**: I agree with you. Would just be nice to have some sort of way or knowing how many of each type (for analytics)  we have in a area or have some way to message just that group in the future. Your suggestions are welcome.
```

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
