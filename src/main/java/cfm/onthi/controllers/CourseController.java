package cfm.onthi.controllers;

import cfm.onthi.dtos.*;
import cfm.onthi.dtos.base.InputCondition;
import cfm.onthi.exceptions.GlobalExceptionHandlerController;
import cfm.onthi.services.CloudinaryService;
import cfm.onthi.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/service-onthi/api")
@RequiredArgsConstructor
public class CourseController {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerController.class);

    private CourseService courseService;
    private CloudinaryService cloudinaryService;

    @Autowired
    public CourseController(CourseService courseService, CloudinaryService cloudinaryService) {
        this.courseService = courseService;
        this.cloudinaryService = cloudinaryService;
    }

    @PostMapping("/courses")
    public ResponseEntity<?> getListCoursesByInputCondition (@RequestBody InputCondition inputCondition) {
        return ResponseEntity.status(HttpStatus.OK).body((courseService.getListCoursesByInputCondition(inputCondition)));
    }


    @GetMapping("/guest/getDetailCourse/{idCourse}")
    public ResponseEntity<?> guestGetDetailCourse(@PathVariable("idCourse") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body((courseService.guestGetDetailCourse(id)));
    }

    @GetMapping("/admin/getDetailCourse/{idCourse}")
    public ResponseEntity<?> adminGetDetailCourse(@PathVariable("idCourse") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body((courseService.adminGetDetailCourse(id)));
    }

    @GetMapping("/user/getDetailCourse/{idCourse}")
    public ResponseEntity<?> userGetDetailCourse(@PathVariable("idCourse") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body((courseService.userGetDetailCourse(id)));
    }

    @GetMapping("/user/getReview/{idCourse}")
    public ResponseEntity<?> userGetListReview(@PathVariable("idCourse") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body((courseService.userGetListReview(id)));

    }

    @GetMapping("/user/getComment/{idLesson}")
    public ResponseEntity<?> userGetListComment(@PathVariable("idLesson") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body((courseService.userGetListComment(id)));

    }

    @PostMapping("/user/likeDislikeReview")
    public ResponseEntity<?> likeDislikeReview (@RequestBody ReviewDTO reviewDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.userLikeDislikeReview(reviewDTO));
    }

    @PostMapping("/user/userUnlikeUndislikeReview")
    public ResponseEntity<?> userUnlikeUndislikeReview (@RequestBody ReviewDTO reviewDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.userUnlikeUndislikeReview(reviewDTO));
    }

    @PostMapping("/user/createReview")
    public ResponseEntity<?> createReview (@RequestBody ReviewDTO reviewDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.createReview(reviewDTO));
    }

    @DeleteMapping("/user/deleteReview/{id}")
    public ResponseEntity<?> deleteReview (@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.deleteReview(id));
    }

    @GetMapping("/user/getLesson/{idCourse}")
    public ResponseEntity<?> userGetListLesson(@PathVariable("idCourse") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body((courseService.userGetLessons(id)));
    }

    @PostMapping("/upload/image")
    @ResponseBody
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile file) throws IOException {
        return ResponseEntity.ok(cloudinaryService.uploadFile(file));
    }

    @PostMapping("/upload/video")
    @ResponseBody
    public ResponseEntity<?> uploadVideo(@RequestParam MultipartFile file) throws IOException {
        return ResponseEntity.ok(cloudinaryService.uploadVideo(file));
    }

    @GetMapping("/user/userGetLessonDetail/{idLesson}")
    public ResponseEntity<?> userGetLessonDetail(@PathVariable("idLesson") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body((courseService.userGetLessonDetail(id)));
    }

    @GetMapping("/user/getListCoursesByUserId/{idUser}")
    public ResponseEntity<?> getCourseByUserId(@PathVariable("idUser") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body((courseService.getCourseByUserID(id)));
    }

    @PostMapping("/user/enrollCourse")
    public ResponseEntity<?> uploadVideo(@RequestBody UserCourseDTO userCourseDTO) throws IOException {
        return ResponseEntity.ok(courseService.enrollCourse(userCourseDTO));
    }

    @PostMapping("/user/updateLearningProcess")
    public ResponseEntity<?> updateLearningProcess(@RequestBody UserCourseDTO userCourseDTO) throws IOException {
        return ResponseEntity.ok(courseService.updateLearningProcess(userCourseDTO));
    }

    @PostMapping("/user/saveNote")
    public ResponseEntity<?> saveNote(@RequestBody NoteDTO noteDTO) throws IOException {
        return ResponseEntity.ok(courseService.saveNote(noteDTO));
    }

    @PostMapping("/user/updateNote")
    public ResponseEntity<?> updateNote(@RequestBody NoteDTO noteDTO) throws IOException {
        return ResponseEntity.ok(courseService.updateNote(noteDTO));
    }

    @PostMapping("/user/getNote")
    public ResponseEntity<?> getNote(@RequestBody InputCondition inputCondition) throws IOException {
        return ResponseEntity.ok(courseService.getNote(inputCondition));
    }

    @PostMapping("/user/getListExercise")
    public ResponseEntity<?> getListExercise(@RequestBody InputCondition inputCondition) throws IOException {
        return ResponseEntity.ok(courseService.getListExercise(inputCondition));
    }

    @GetMapping("/user/getDetailExercise/{idExercise}")
    public ResponseEntity<?> getDetailExercise(@PathVariable("idExercise") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body((courseService.getDetailExercise(id)));
    }

    @GetMapping("/user/getAllCourse")
    public ResponseEntity<?> getAllCourse() {
        return ResponseEntity.status(HttpStatus.OK).body((courseService.getAllCourse()));
    }

    @PostMapping("/admin/activeCourse")
    public ResponseEntity<?> activeCourse(@RequestBody Long id) throws IOException {
        return ResponseEntity.ok(courseService.activeCourse(id));
    }

    @PostMapping("/admin/inactiveCourse")
    public ResponseEntity<?> inactiveCourse(@RequestBody Long id) throws IOException {
        return ResponseEntity.ok(courseService.inactiveCourse(id));
    }

    @GetMapping("/teacher/getListUser/{idTeacher}")
    public ResponseEntity<?> getStudentsByTeacherId(@PathVariable("idTeacher") Long idTeacher) {
        return ResponseEntity.status(HttpStatus.OK).body((courseService.getStudentsByTeacherId(idTeacher)));
    }

    @PostMapping("/teacher/addCourse")
    public ResponseEntity<?> teacherAddCourse(@RequestBody CourseDTO courseDTO) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body((courseService.teacherAddCourse(courseDTO)));
    }

    @PostMapping("/teacher/addLessonParents")
    public ResponseEntity<?> teacherAddLessonParents(@RequestBody List<LessonDTO> lessonDTOList) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body((courseService.teacherAddLessonParents(lessonDTOList)));
    }

    @PostMapping("/teacher/unSubmitCourse")
    public ResponseEntity<?> unSubmitCourse(@RequestBody Long id) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body((courseService.unSubmitCourse(id)));
    }

    @PostMapping("/teacher/submitCourse")
    public ResponseEntity<?> submitCourse(@RequestBody Long id) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body((courseService.submitCourse(id)));
    }

    @PostMapping("/teacher/updateCourse")
    public ResponseEntity<?> updateCourse(@RequestBody CourseDTO courseDTO) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body((courseService.updateCourse(courseDTO)));
    }
}
