# --- !Ups

insert into "albums"
("name", "year", "description")
values
('Whatever People Say I Am, That''s What I''m Not', 2004, ''),
('AM', 2013, 'Album released in 2013');

insert into "musics"
("album_id", "title", "lyrics")
values

-- A Certain Romance
(1, 'A Certain Romance',
'Well oh they might wear classic Reeboks
Or knackered Converse
Or tracky bottoms tucked in socks
But all of that''s what the point is not
The point is that there ain''t no romance around there

And there''s the truth that they can''t see
They''d probably like to throw a punch at me
And if you could only see them, then you would agree
Agree that there ain''t no romance around there

You know, oh, it''s a funny thing, you know?
We''ll tell them if you like
We''ll tell them all tonight
They''ll never listen
Because their minds are made up
And course it''s all OK to carry on that way

''Cause over there there''s broken bones
There''s only music, so that there''s new ringtones
And it don''t take no Sherlock Holmes
To see it''s a little different around here

Don''t get me wrong though there''s boys in bands
And kids who like to scrap with pool cues in their hands
And just ''cause he''s had a couple of cans
He thinks it''s alright to act like a dickhead

Don''t you know, oh it''s a funny thing you know
We''ll tell them if you like
We''ll tell them all tonight
They''ll never listen
''Cause their minds are made up
And course is all OK to carry on that way
But I said no, oh no!
Well oh, you won''t get me to go
Not anywhere, not anywhere
No, I won''t go, oh no, no?

But over there there''s friends of mine
What can I say, I''ve known them for a long long time
And yet they might overstep the line
But you just cannot get angry in the same way
No, not in the same way
No not in the same way
Oh no, oh no, no!'),

-- Do I Wanna Know
(2, 'Do I Wanna Know?',
'Have you got colour in your cheeks?
Do you ever get that fear that you can''t shift
The type that sticks around like something in your teeth?
Are there some aces up your sleeve?
Have you no idea that you''re in deep?
I dreamt about you nearly every night this week
How many secrets can you keep?
''Cause there''s this tune I found that makes me think of you somehow and I play it on repeat
Until I fall asleep
Spilling drinks on my settee

(Do I wanna know)
If this feeling flows both ways?
(Sad to see you go)
Was sort of hoping that you''d stay
(Baby we both know)
That the nights were mainly made for saying things that you can''t say tomorrow day

Crawling back to you

Ever thought of calling when you''ve had a few?
''Cause I always do
Maybe I''m too busy being yours to fall for somebody new
Now I''ve thought it through

Crawling back to you

So have you got the guts?
Been wondering if your heart''s still open and if so I wanna know what time it shuts
Simmer down and pucker up
I''m sorry to interrupt. It''s just I''m constantly on the cusp of trying to kiss you
I don''t know if you feel the same as I do
But we could be together if you wanted to

(Do I wanna know?)
If this feeling flows both ways?
(Sad to see you go)
Was sort of hoping that you''d stay
(Baby we both know)
That the nights were mainly made for saying things that you can''t say tomorrow day

Crawling back to you (crawling back to you)

Ever thought of calling when you''ve had a few? (you''ve had a few)
''Cause I always do (''cause I always do)
Maybe I''m too (maybe I''m too busy) busy being yours to fall for somebody new
Now I''ve thought it through

Crawling back to you

(Do I wanna know?)
If this feeling flows both ways?
(Sad to see you go)
Was sort of hoping that you''d stay
(Baby we both know)
That the nights were mainly made for saying things that you can''t say tomorrow day

(Do I wanna know?)
Too busy being yours to fall
(Sad to see you go)
Ever thought of calling darling?
(Do I wanna know?)
Do you want me crawling back to you?'),

-- Do I Wanna Know
(2, 'R U Mine?',
'I''m a puppet on a string
Tracy Island, time-traveling
Diamond cutter shaped heartaches
Come to find you four in some velvet morning
Years too late
She''s a silver lining lone ranger riding
Through an open space
In my mind when she''s not right there beside me

I go crazy ''cause here isn''t where I wanna be
And satisfaction feels like a distant memory
And I can''t help myself,
All I wanna hear her say is "Are you mine?"

Well, Are you mine?
Are you mine?
Are you mine?

I guess what I''m trying to say is I need the deep end
Keep imagining meeting, wished away entire lifetimes
Unfair we''re not somewhere misbehaving for days
Great escape lost track of time and space
She''s a silver lining climbing on my desire

And I go crazy ''cause here isn''t where I wanna be
And satisfaction feels like a distant memory
And I can''t help myself,
All I wanna hear her say is "Are you mine?"

Well, are you mine? (Are you mine tomorrow?)
Are you mine? (Or just mine tonight?)
Are you mine? (Are you mine? Mine?)

And the thrill of the chase moves in mysterious ways
So in case I''m mistaken,
I just wanna hear you say you got me baby
Are you mine?

She''s a silver lining lone ranger riding through an open space
In my mind when she''s not right there beside me
And I go crazy cause here isn''t where I wanna be

And satisfaction feels like a distant memory
And I can''t help myself,
All I wanna hear her say is "Are you mine?"

Well, are you mine? (Are you mine tomorrow?)
Are you mine? (Or just mine tonight?)
Are you mine? (Are you mine tomorrow, or just mine tonight?)');

# --- !Downs

-- NOTE: This is not very accurate...
delete from "musics" where "id" between 1 and 2;
