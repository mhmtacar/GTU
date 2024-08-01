
%	room(ID,Capacity,Equipment).

room(z06, 60, handicapped).
room(z06, 60, projector).
room(z11, 70, handicapped).
room(z11, 70, smartboard).
room(z07, 50, projector).


%	course(ID,Instructor,Capacity,Hour,Room,Equipment).

course(cse321,gozupek,45,3,z06,projector).
course(cse331,bayrakci,55,3,z06,_).
course(cse341,genc,60,4,z11,smartboard).
course(cse343,kalkan,30,2,z11,smartboard).
course(cse101,gokturk,50,2,z06,_).
course(cse102,kaya,40,4,z07,projector).


%	instructor(ID,Courses,Preference).

instructor(gozupek,cse321,projector).
instructor(bayrakci,cse331,_).
instructor(genc,cse341,smartboard).
instructor(kalkan,cse343,smartboard).
instructor(gokturk,cse101,_).
instructor(kaya,cse102,projector).


%	student(ID,Courses,Handicapped).

student(1, [cse101], no_hcapped).
student(2, [cse101, cse102], no_hcapped).
student(3, [cse102], hcapped).
student(4, [cse321], no_hcapped).
student(5, [cse321, cse331], no_hcapped).
student(6, [cse321, cse341], hcapped).
student(7, [cse321, cse343], no_hcapped).
student(8, [cse321, cse331, cse341], no_hcapped).
student(9, [cse321, cse331, cse343], hcapped).
student(10, [cse321, cse341, cse343], no_hcapped).
student(11, [cse331], no_hcapped).
student(12, [cse331, cse341], hcapped).
student(13, [cse331, cse343], no_hcapped).
student(14, [cse331, cse341, cse343], hcapped).
student(15, [cse341], hcapped).
student(16, [cse341, cse343], no_hcapped).
student(17, [cse343], no_hcapped).


%	occupancy(RoomID,Hour,CourseID).

occupancy(z06,8,cse101).
occupancy(z06,9,cse101).
occupancy(z06,8,cse321).
occupancy(z06,9,cse321).
occupancy(z06,10,cse321).
occupancy(z06,11,empty).
occupancy(z06,12,empty).
occupancy(z06,13,empty).
occupancy(z06,14,cse331).
occupancy(z06,15,cse331).
occupancy(z06,16,cse331).
occupancy(z11,8,cse341).
occupancy(z11,9,cse341).
occupancy(z11,10,cse341).
occupancy(z11,11,cse341).
occupancy(z11,12,empty).
occupancy(z11,13,empty).
occupancy(z11,14,empty).
occupancy(z11,15,cse343).
occupancy(z11,16,cse343).
occupancy(z07,13,cse102).
occupancy(z07,14,cse102).
occupancy(z07,15,cse102).
occupancy(z07,16,cse102).



conflict(CourseID1,CourseID2) :- occupancy(X,Y,CourseID1),occupancy(X,Y,CourseID2),!, not(CourseID1 = CourseID2).


assign(RoomID,CourseID) :- room(RoomID,X,Equipment), course(CourseID,_,Y,_,_,Equipment), Y=<X.


enroll(StudentID,CourseID) :- (student(StudentID,CourseID,Hcapped_Condition1),Hcapped_Condition1 == no_hcapped);
							  (student(StudentID,CourseID,Hcapped_Condition1),Hcapped_Condition1 == hcapped) -> (course(CourseID,_,_,_,RoomID,_) -> room(RoomID,_,Hcapped_Condition2) -> Hcapped_Condition2 == handicapped).





 


