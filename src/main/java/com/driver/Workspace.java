package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import javax.lang.model.type.ArrayType;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar;

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId, Integer.MAX_VALUE);
        this.calendar = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(meeting);
    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // at a particular time, you can only be present in at most one meeting
        // if you want to attend a meeting, you must join it at its start time and leave at end time.

        List<Meeting> sortedCalender = new ArrayList<>(List.copyOf(calendar));
        Collections.sort(sortedCalender, (x,y) -> x.getEndTime().compareTo(y.getEndTime()) );

//        ArrayList<Pair<LocalTime, Integer>> endTimes = new ArrayList<>();

//        for (int i = 0; i < calendar.size(); i++) {
//            endTimes.add(Pair.of(calendar.get(i).getEndTime(), i));
//        }
//
//        Collections.sort(endTimes);
//
//        LocalTime time_limit = endTimes.get(0).getLeft();
        List<Meeting> maxMeetings = new ArrayList<>();
        LocalTime time_limit = sortedCalender.get(0).getStartTime();
//        int cnt = 0;
//        if(!sortedCalender.isEmpty()) {
//            cnt += 1;
//        }


        for (Meeting meeting: sortedCalender) {
            if (meeting.getStartTime().compareTo(time_limit) >= 0) {
                maxMeetings.add(meeting);
                time_limit = meeting.getStartTime();
            }
        }

        return maxMeetings.size();
    }
}