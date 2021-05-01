# Meeting_Assistant

## Unit test
   Four Unit tests to test Repository of project

## /adduser
  Adds a new user to Database
  E.g. Payload : 
  {
    "owner": "user2"
  }

## /setmeet
  Adds a new meeting, given both the owner and attendee are present in DB, else add the new User and try again
  E.g. Payload:
  {
    "calendarBean": {
        "owner": "user1"
    },
    "eventBean": {
        "startDateTime": 14.0,
        "endDateTime": 16.0,
        "attendee": "user2"
    }
  }
  
## /free
  Given 2 calendars, and duration, returns the free slots... If no slot availaible returns empty
    E.g. Payload:
    {
    "user_events": [
        {
            "startDateTime": 5.0,
            "endDateTime": 6.0,
            "attendee": "user2"
        },
        {
            "startDateTime": 14.0,
            "endDateTime": 15.0,
            "attendee": "user2"
        }
    ],
    "second_user_events": [
        {
            "startDateTime": 12.0,
            "endDateTime": 13.0,
            "attendee": "user2"
        },
        {
            "startDateTime": 15.0,
            "endDateTime": 16.0,
            "attendee": "user2"
        }
    ],
    "duration": 30
}

## /conflict
  Given a new meeting request, returns the conflict participants, if no conflict then return a string "No Conflicts"
  {
    "calendarBean": {
        "owner": "user1"
    },
    "eventBean": {
        "startDateTime": 14.0,
        "endDateTime": 16.0,
        "attendee": "user2"
    }
}
