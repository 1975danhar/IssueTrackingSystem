---------------------------------------------------------------
-- Creates objects (tables etc) corresponding to the database
-- schema for the Issue Tracking System ITS.
---------------------------------------------------------------


---------------------------------------------------------------
-- ProcessLeader
--
CREATE TABLE ProcessLeader (
    leaderId SERIAL PRIMARY KEY,
    firstName VARCHAR(30),
    lastName VARCHAR(30)
);

---------------------------------------------------------------
-- Skill
--
CREATE TABLE Skill (
    skillId SERIAL PRIMARY KEY,
    skillName VARCHAR(15)
);

---------------------------------------------------------------
-- ITStaff
--
CREATE TABLE ITStaff (
    itStaffId SERIAL PRIMARY KEY,
    firstName VARCHAR(30),
    lastName VARCHAR(30)
);

---------------------------------------------------------------
-- StaffSkill
--
CREATE TABLE StaffSkill (
    staffSkillId SERIAL PRIMARY KEY,
    skillId INTEGER NOT NULL,
    itStaffId INTEGER NOT NULL,
    FOREIGN KEY(skillId) REFERENCES Skill(skillId),
    FOREIGN KEY(itStaffId) REFERENCES ITStaff(itStaffId)
);

---------------------------------------------------------------
-- Case
--
CREATE TABLE Cases (
    caseId SERIAL PRIMARY KEY,
    caseAlias VARCHAR(15),
    caseStatus VARCHAR(10),
    leaderId INTEGER NOT NULL,
    caseNotes VARCHAR(300),
    FOREIGN KEY(leaderId) REFERENCES ProcessLeader(leaderId)
);

-- Assignment
--
CREATE TABLE Assignments (
    assignmentId SERIAL PRIMARY KEY,
    estimatedHours INTEGER,
    actualHours INTEGER,
    costPerHour INTEGER,
    assignmentStatus VARCHAR(15),
    caseId INTEGER NOT NULL,
    itStaffId INTEGER,
    skillId INTEGER,
    assignmentNotes VARCHAR(300),
    FOREIGN KEY(caseId) REFERENCES Cases(caseId),
    FOREIGN KEY(itStaffId) REFERENCES ITStaff(itStaffId),
    FOREIGN KEY(skillId) REFERENCES Skill(skillId)
);


