-- Table: "ITS"."Assignment"

-- DROP TABLE "ITS"."Assignment";

CREATE TABLE "ITS"."Assignment"
(
  "assignmentId" serial NOT NULL,
  "estHours" smallint NOT NULL,
  "actualHours" smallint NOT NULL,
  "costPerHour" integer NOT NULL,
  "assignmentStatus" character varying(40) NOT NULL,
  CONSTRAINT "Assignment_pkey" PRIMARY KEY ("assignmentId")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "ITS"."Assignment"
  OWNER TO postgres;
GRANT ALL ON TABLE "ITS"."Assignment" TO postgres;



-- Table: "ITS"."Case"

-- DROP TABLE "ITS"."Case";

CREATE TABLE "ITS"."Case"
(
  "caseId" serial NOT NULL,
  "caseAlias" character varying(30) NOT NULL,
  "caseStatus" character varying(30) NOT NULL,
  CONSTRAINT "Case_pkey" PRIMARY KEY ("caseId"),
  CONSTRAINT "Case_caseId_fkey" FOREIGN KEY ("caseId")
      REFERENCES "ITS"."Assignment" ("assignmentId") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "ITS"."Case"
  OWNER TO postgres;
GRANT ALL ON TABLE "ITS"."Case" TO postgres;



-- Table: "ITS"."ItStaff"

-- DROP TABLE "ITS"."ItStaff";

CREATE TABLE "ITS"."ItStaff"
(
  "itStaffId" serial NOT NULL,
  "fName" character(30) NOT NULL,
  "lName" character(30) NOT NULL,
  CONSTRAINT "ItStaff_pkey" PRIMARY KEY ("itStaffId"),
  CONSTRAINT "ItStaff_itStaffId_fkey" FOREIGN KEY ("itStaffId")
      REFERENCES "ITS"."Assignment" ("assignmentId") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "ITS"."ItStaff"
  OWNER TO postgres;
GRANT ALL ON TABLE "ITS"."ItStaff" TO postgres;


-- Table: "ITS"."Processleader"

-- DROP TABLE "ITS"."Processleader";

CREATE TABLE "ITS"."Processleader"
(
  "leaderId" serial NOT NULL,
  "fName" character(30) NOT NULL,
  "lName" character(30) NOT NULL,
  CONSTRAINT "Processleader_pkey" PRIMARY KEY ("leaderId"),
  CONSTRAINT "Processleader_leaderId_fkey" FOREIGN KEY ("leaderId")
      REFERENCES "ITS"."Case" ("caseId") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "ITS"."Processleader"
  OWNER TO postgres;
GRANT ALL ON TABLE "ITS"."Processleader" TO postgres;


-- Table: "ITS"."Skill"

-- DROP TABLE "ITS"."Skill";

CREATE TABLE "ITS"."Skill"
(
  "skillId" serial NOT NULL,
  "skillName" character varying(100) NOT NULL,
  CONSTRAINT "Skill_pkey" PRIMARY KEY ("skillId"),
  CONSTRAINT "Skill_skillId_fkey" FOREIGN KEY ("skillId")
      REFERENCES "ITS"."Assignment" ("assignmentId") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "ITS"."Skill"
  OWNER TO postgres;
GRANT ALL ON TABLE "ITS"."Skill" TO postgres;


-- Table: "ITS"."Staffskill"

-- DROP TABLE "ITS"."Staffskill";

CREATE TABLE "ITS"."Staffskill"
(
  "staffSkillId" serial NOT NULL,
  CONSTRAINT "Staffskill_pkey" PRIMARY KEY ("staffSkillId")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "ITS"."Staffskill"
  OWNER TO postgres;
GRANT ALL ON TABLE "ITS"."Staffskill" TO postgres;











