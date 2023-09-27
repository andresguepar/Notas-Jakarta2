package com.example.notasjakarta.repository.impl;

import com.example.notasjakarta.domain.model.Grade;
import com.example.notasjakarta.domain.model.Student;
import com.example.notasjakarta.domain.model.Subject;
import com.example.notasjakarta.domain.model.Teacher;
import com.example.notasjakarta.mapping.dtos.GradeDto;
import com.example.notasjakarta.mapping.mapper.GradeMapper;
import com.example.notasjakarta.repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradeRepositoryImpl implements Repository<GradeDto> {
    private Connection conn;

    public GradeRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    private GradeDto createGrade(ResultSet resultSet) throws
            SQLException {
        Grade grade = new Grade();
        grade.setId(resultSet.getLong("id"));
        grade.setGrade(resultSet.getDouble("grade"));

        Student student = new Student();
        student.setId(resultSet.getLong("id_student"));
        student.setName(resultSet.getString("name"));
        student.setEmail(resultSet.getString("emailS"));
        student.setSemester(resultSet.getString("semester"));

        grade.setStudent(student);

        Subject subject = new Subject();
        subject.setId(resultSet.getLong("id_subject"));
        subject.setName(resultSet.getString("subject"));

        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getLong("id_teacher"));
        teacher.setName(resultSet.getString("teacher"));
        teacher.setEmail(resultSet.getString("email"));
        subject.setTeacher(teacher);

        grade.setSubject(subject);
        return GradeMapper.mapFrom(grade);
    }

    @Override
    public List<GradeDto> list() {
        List<GradeDto> gradeList = new ArrayList<>();
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT g.*,st.name, st.email AS emailS, st.semester, " +
                     "sb.name AS subject, t.id_teacher, t.name AS teacher, t.email FROM grades g INNER JOIN students st " +
                     "INNER JOIN subjects sb INNER JOIN teachers t ON (g.id_student = st.id  " +
                     "AND g.id_subject = sb.id_subject AND sb.id_teacher = t.id_teacher);")) {
            while (resultSet.next()) {
                GradeDto grade = createGrade(resultSet);
                gradeList.add(grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return gradeList;
    }

    @Override
    public GradeDto byId(Long id) {
        GradeDto grade = null;
        try (PreparedStatement preparedStatement = conn
                .prepareStatement("SELECT g.*,st.name, st.email AS emailS, st.semester, sb.name AS subject, t.name " +
                        "AS teacher, t.email FROM grades g INNER JOIN students st INNER JOIN subjects sb " +
                        "INNER JOIN teachers t ON (g.id_student = st.id  AND " +
                        "g.id_subject = sb.id_subject AS id_subject AND sb.id_teacher = t.id_teacher AND g.id=?);"))
        {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                grade = createGrade(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grade;
    }

    @Override
    public void add(GradeDto gradeDto) {
        String sql;
        Grade grade = null;
        if (grade.getId() != null && grade.getId()>0 ) {
            sql = "UPDATE grades SET id_student=?,id_subject=?,grade=? WHERE id=?";
        }else {
            sql = "INSERT INTO grades (id_student,id_subject,grade) VALUES (?,?,?);";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, grade.getStudent().getId());
            stmt.setLong(2, grade.getSubject().getId());
            stmt.setDouble(3, grade.getGrade());

            if (grade.getId() != null && grade.getId()>0){
                stmt.setLong(4, grade.getId());
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM grades WHERE id =?")){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
