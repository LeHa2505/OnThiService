package cfm.onthi.controllers;

import cfm.onthi.dtos.ReviewDTO;
import cfm.onthi.dtos.UserCourseDTO;
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
}
