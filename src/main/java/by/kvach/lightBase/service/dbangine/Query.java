package by.kvach.lightBase.service.dbangine;

public class Query {

    public static String candidateInfo(String name) {
        return "SELECT com.CandidateId AS idCandidate, c.Name AS candidateName, vr.Title AS vacancy, p.Name AS positionName, el.Name AS englishLevel, e.Name AS experience, anu.UserName AS hr, c.IsPrimary, ct.Name AS contactName, cc.Value AS contactValue, cc.IsPrimary FROM Candidates c JOIN CandidateContacts cc ON c.Id = cc.CandidateId JOIN ContactTypes ct ON cc.ContactTypeId = ct.Id JOIN Communications com ON c.Id = com.CandidateId JOIN Positions p ON com.PositionId = p.Id JOIN EnglishLevels el ON com.EnglishLevelId = el.Id JOIN Experience e ON com.ExperienceId = e.Id JOIN AspNetUsers anu ON com.HrManagerAspNetUserId = anu.Id JOIN VacancyRequests vr ON com.VacancyRequestId = vr.Id WHERE c.Name = N'" + name + "'";
    }

    public static String candidateInfoFofAPI(String name) {
        return "SELECT com.CandidateId AS idCandidate, c.Name AS candidateName, p.Name AS positionName, e.Name AS experience, anu.UserName AS hr, c.IsPrimary, ct.Name AS contactName, cc.Value  AS contactValue, cc.IsPrimary FROM Candidates c JOIN CandidateContacts cc ON c.Id = cc.CandidateId JOIN ContactTypes ct ON cc.ContactTypeId = ct.Id JOIN Communications com ON c.Id = com.CandidateId JOIN Positions p ON com.PositionId = p.Id JOIN Experience e ON com.ExperienceId = e.Id JOIN AspNetUsers anu ON com.HrManagerAspNetUserId = anu.Id WHERE c.Name = '" + name + "'";
    }

    public static String vacancyInfo(String name) {
        return "SELECT vr.Id AS idVacancy, vr.Title AS title, vr.IsInWork AS isInWork, anu.UserName AS creator, vr.PositionName AS position, l.Name AS level, vr.Project AS project, vr.EnglishLevel AS englishLevel, vr.Interviewer AS interviewer, vr.RequiredTechnologies AS requiredTechnologies, vr.DesiredTechnologies AS desiredTechnologies FROM VacancyRequests vr JOIN AspNetUsers anu ON vr.CreatorId = anu.Id JOIN Positions p ON vr.PositionId = P.Id JOIN Levels l ON vr.LevelId = L.Id JOIN AspNetUserRoles anur ON anu.Id = anur.UserId WHERE vr.Title = '" + name + "'";
    }

    public static String getPositions(boolean b) {
        return "SELECT pos.Name FROM Positions pos WHERE IsActiveForVacancy = '" + b + "';";
    }

    public static String getActivePosition(boolean b) {
        return "SELECT c.Id  AS candidateId, c.Name AS candidateName, p.Name AS positionName, engLev.Name AS candidateEnglishLevel, comments.Description AS commentsFROM Candidates c JOIN Communications com ON c.Id = com.CandidateId JOIN EnglishLevels engLev ON com.EnglishLevelId = engLev.Id JOIN Positions p ON com.PositionId = p.Id JOIN Comments comments ON com.Id = comments.CommunicationIdWHERE p.IsActiveForVacancy = '" + b + "';";
    }

    public static String getContacts(String name) {
        return "SELECT c.Name AS candidateName, com.CandidateId AS idCandidate, ct.Name AS contactName, cc.Value AS contactValue, cc.IsPrimary FROM Communications com JOIN Candidates c ON c.Id = com.CandidateId JOIN CandidateContacts cc ON c.Id = cc.CandidateId JOIN ContactTypes ct ON cc.ContactTypeId = ct.Id WHERE c.Name = N'" + name + "'";
    }

    public static String getRandomVacancy() {
        return "SELECT TOP 1 vac.Title AS vacancy FROM VacancyRequests vac WHERE IsInWork = 'true' ORDER BY NEWID()";
    }

    public static String getRandomPosition() {
        return "SELECT TOP 1 pos.Name AS position FROM Positions pos WHERE IsActiveForVacancy = 'true' ORDER BY NEWID()";
    }

    public static String getRandomLevel() {
        return "SELECT TOP 1 lev.Name AS level FROM Levels lev ORDER BY NEWID()";
    }

    public static String getRandomExperience() {
        return "SELECT TOP 1 exp.Name AS positon FROM Experience exp ORDER BY NEWID()";
    }

    public static String getRandomHR() {
        return "SELECT TOP 1 UserName AS userName FROM AspNetUserRoles anur JOIN AspNetRoles anr on anur.RoleId = anr.Id JOIN AspNetUsers anu ON anur.UserId = anu.Id WHERE Name = 'HR' AND Email IS NOT NULL AND IsConfirmed = 'true' ORDER BY NEWID() ";
    }

    public static String getRandomEnglishLevel() {
        return "SELECT TOP 1 enlev.Name AS englishLevel FROM EnglishLevels enlev ORDER BY NEWID()";
    }

    public static String getRandomSourceType() {
        return "SELECT TOP 1 sourceType.Name AS sourceType FROM SourceTypes sourceType ORDER BY NEWID()";
    }

    public static String getRandomSourceContactType() {
        return "SELECT TOP 1 sct.Name AS sourceContactType FROM SourceContactTypes sct ORDER BY NEWID()";
    }

    public static String getRandomCommunicationStatus() {
        return "SELECT TOP 1 cs.Name AS communicationStatus FROM CommunicationStatuses cs ORDER BY NEWID()";
    }

    public static String getRandomInterviewer() {
        return "SELECT TOP 1 UserName FROM AspNetUserRoles JOIN AspNetRoles ANR on AspNetUserRoles.RoleId = ANR.Id JOIN AspNetUsers ON AspNetUserRoles.UserId = AspNetUsers.Id WHERE Name = 'Interviewer' AND CanSeeSalary = 'false' AND IsConfirmed = 'true' ORDER BY NEWID()";
    }

    /////////////////////////////// PROCEDURES ////////////////////////////////

    public static String deleteCandidateProcedure(int id){
        return "EXEC sp_deleteCandidate " + id;
    }

}
