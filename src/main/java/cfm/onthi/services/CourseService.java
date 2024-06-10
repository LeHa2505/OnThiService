package cfm.onthi.services;

import cfm.onthi.dtos.*;
import cfm.onthi.dtos.base.InputCondition;
import cfm.onthi.dtos.base.ResponseDTO;
import cfm.onthi.repositories.*;
import org.jooq.Condition;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

public interface CourseService {
    ResponseDTO getListCoursesByInputCondition(InputCondition inputCondition);
    ResponseDTO guestGetDetailCourse(Long id);
    ResponseDTO userGetDetailCourse(Long id);
    ResponseDTO userLikeDislikeReview(ReviewDTO reviewDTO);
    ResponseDTO userUnlikeUndislikeReview(ReviewDTO reviewDTO);
    ResponseDTO userGetListReview(Long id);
    ResponseDTO userGetListComment(Long id);
    ResponseDTO userGetLessons(Long id);
    ResponseDTO userGetLessonDetail(Long id);
    ResponseDTO getCourseByUserID(Long id);
    ResponseDTO createReview(ReviewDTO reviewDTO);
    ResponseDTO deleteReview(Long  id);
    ResponseDTO enrollCourse(UserCourseDTO userCourseDTO);
    ResponseDTO updateLearningProcess(UserCourseDTO userCourseDTO);
    ResponseDTO saveNote(NoteDTO noteDTO);
    ResponseDTO updateNote(NoteDTO noteDTO);
    ResponseDTO getNote(InputCondition inputCondition);
    ResponseDTO getListExercise(InputCondition inputCondition);
    ResponseDTO getDetailExercise(Long id);
}

@Service
class CourseServiceImpl extends BaseService implements CourseService {
    private CourseRepository courseRepository;
    private UserRepository userRepository;
    private LessonRepository lessonRepository;
    private QuizRepository quizRepository;
    private ReviewRepository reviewRepository;
    private DocumentRepository documentRepository;
    private NoteRepository noteRepository;
    private ReviewUserRepository reviewUserRepository;
    private UserCourseRepository userCourseRepository;
    private ExerciseRepository exerciseRepository;

    public CourseServiceImpl(
            TransactionTemplate transactionTemplate,
            CourseRepository courseRepository,
            UserRepository userRepository,
            LessonRepository lessonRepository,
            QuizRepository quizRepository,
            ReviewRepository reviewRepository,
            DocumentRepository documentRepository,
            NoteRepository noteRepository,
            ReviewUserRepository reviewUserRepository,
            UserCourseRepository userCourseRepository,
            ExerciseRepository exerciseRepository
    ) {
        super();
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
        this.quizRepository = quizRepository;
        this.reviewRepository = reviewRepository;
        this.documentRepository = documentRepository;
        this.noteRepository = noteRepository;
        this.reviewUserRepository = reviewUserRepository;
        this.userCourseRepository = userCourseRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public ResponseDTO getListCoursesByInputCondition(InputCondition inputCondition) {
        List<CourseDTO> courseDTOList = this.courseRepository.getListByInputCondition(inputCondition);
        if (courseDTOList != null && !courseDTOList.isEmpty()) {
            return new ResponseDTO(true, "Đã lấy được danh sách khóa học bạn cần!", courseDTOList);
        } else {
            return new ResponseDTO(true, "Không có kết quả bạn mong muốn! Chúng tôi sẽ cập nhật thêm khóa học!", null);
        }
    }

    @Override
    public ResponseDTO guestGetDetailCourse(Long id) {
        CourseDTO courseDTO = this.courseRepository.guestGetByID(id);
        if (courseDTO != null) {
            return new ResponseDTO(true, "OK!", courseDTO);
        }
        return new ResponseDTO(false, "Có lỗi xảy ra!", null);
    }

    @Override
    public ResponseDTO userGetDetailCourse(Long id) {
        CourseDTO courseDTO = this.courseRepository.getByID(id);
        if (courseDTO != null) {
            return new ResponseDTO(true, "OK!", courseDTO);
        }
        return new ResponseDTO(false, "Có lỗi xảy ra!", null);
    }

    @Override
    public ResponseDTO userLikeDislikeReview(ReviewDTO reviewDTO) {
        if (reviewRepository.update(reviewDTO)) {
            ReviewUserDTO reviewUserDTO = new ReviewUserDTO();
            reviewUserDTO.idReview = reviewDTO.idReview;
            reviewUserDTO.idUser =  reviewDTO.IdUserInteract;
            reviewUserDTO.action = reviewDTO.userInteractAction;
            if (reviewUserRepository.save(reviewUserDTO)) {
                return new ResponseDTO(true, "OK!", null);
            }
            else {
                return new ResponseDTO(false, "Có lỗi xảy ra!", null);
            }
        }
        else {
            return new ResponseDTO(false, "Có lỗi xảy ra!", null);
        }
    }

    @Override
    public ResponseDTO userUnlikeUndislikeReview(ReviewDTO reviewDTO) {
        if (reviewRepository.update(reviewDTO)) {
            InputCondition inputCondition = new InputCondition();
            inputCondition.setID_REVIEW(reviewDTO.idReview);
            inputCondition.setID_USER(reviewDTO.IdUserInteract);
            ReviewUserDTO reviewUserDTO = reviewUserRepository.getByInputCondition(inputCondition);
            if (reviewUserRepository.deleteByID(reviewUserDTO.idReviewUser)) {
                return new ResponseDTO(true, "OK!", null);
            }
            else {
                return new ResponseDTO(false, "Có lỗi xảy ra!", null);
            }
        }
        else {
            return new ResponseDTO(false, "Có lỗi xảy ra!", null);
        }
    }

    @Override
    public ResponseDTO userGetListReview(Long id) {
        List<ReviewDTO> reviewDTOList = reviewRepository.getByIDCourse(id);
        if (reviewDTOList != null && !reviewDTOList.isEmpty()) {
            return new ResponseDTO(true, "OK!", reviewDTOList);
        } else {
            return new ResponseDTO(true, "Chưa có bình luận nào!", null);
        }
    }

    @Override
    public ResponseDTO userGetListComment(Long id) {
        InputCondition inputCondition = new InputCondition();
        inputCondition.ID_LESSON = id;
        List<ReviewDTO> reviewDTOList = reviewRepository.getListByInputCondition(inputCondition);
        if (reviewDTOList != null && !reviewDTOList.isEmpty()) {
            return new ResponseDTO(true, "OK!", reviewDTOList);
        } else {
            return new ResponseDTO(true, "Chưa có câu hỏi nào!", null);
        }
    }

    @Override
    public ResponseDTO userGetLessons(Long id) {
        InputCondition inputCondition = new InputCondition();
        inputCondition.ID_COURSE = id;
        List<LessonDTO> lessonDTOList = lessonRepository.getListByInputCondition(inputCondition);
        return new ResponseDTO(true, "OK!", lessonDTOList);
    }

    @Override
    public ResponseDTO userGetLessonDetail(Long id) {
        LessonDTO lessonDTO = lessonRepository.getByID(id);
        return new ResponseDTO(true, "OK!", lessonDTO);
    }

    @Override
    public ResponseDTO getCourseByUserID(Long id) {
        InputCondition inputCondition = new InputCondition();
        inputCondition.ID_USER = id;
        List<UserCourseDTO> userCourseDTOList = userCourseRepository.getListByInputCondition(inputCondition);
        return new ResponseDTO(true, "OK!", userCourseDTOList);
    }

    @Override
    public ResponseDTO createReview(ReviewDTO reviewDTO) {
        if (reviewRepository.save(reviewDTO)) {
            return new ResponseDTO(true, "Cảm ơn bạn vì đã góp ý về khóa học này!", null);
        }
        else {
            return new ResponseDTO(false, "Có lỗi xảy ra!", null);
        }
    }

    @Override
    public ResponseDTO deleteReview(Long id) {
        InputCondition inputCondition = new InputCondition();
        inputCondition.ID_REVIEW = id;
        List<ReviewUserDTO> reviewUserDTOList = reviewUserRepository.getListByInputCondition(inputCondition);
        if (reviewUserRepository.deleteList(reviewUserDTOList)) {
            if (this.reviewRepository.deleteByID(id))
            {
                return new ResponseDTO(true, "Xóa bình luận thành công!", null);
            }
            else {
                return new ResponseDTO(false, "Có lỗi xảy ra!", null);
            }
        }
        else {
            return new ResponseDTO(false, "Có lỗi xảy ra!", null);
        }
    }

    @Override
    public ResponseDTO enrollCourse(UserCourseDTO userCourseDTO) {
        if (userCourseRepository.save(userCourseDTO)) {
            return new ResponseDTO(true, "Mua khóa học thành công", null);
        }
        else {
            return new ResponseDTO(false, "Có lỗi xảy ra!", null);
        }
    }

    @Override
    public ResponseDTO updateLearningProcess(UserCourseDTO userCourseDTO) {
        if (userCourseRepository.update(userCourseDTO)) {
            return new ResponseDTO(true, "Đánh dấu đã học thành công", null);
        }
        else {
            return new ResponseDTO(false, "Có lỗi xảy ra!", null);
        }
    }

    @Override
    public ResponseDTO saveNote(NoteDTO noteDTO) {
        if (noteRepository.save(noteDTO)) {
            return new ResponseDTO(true, "Lưu ghi chú thành công", null);
        }
        else {
            return new ResponseDTO(false, "Có lỗi xảy ra!", null);
        }
    }

    @Override
    public ResponseDTO updateNote(NoteDTO noteDTO) {
        if (noteRepository.update(noteDTO)) {
            return new ResponseDTO(true, "Cập nhật ghi chú thành công", null);
        }
        else {
            return new ResponseDTO(false, "Có lỗi xảy ra!", null);
        }
    }

    @Override
    public ResponseDTO getNote(InputCondition inputCondition) {
        NoteDTO noteDTO = noteRepository.getByInputCondition(inputCondition);
        return new ResponseDTO(true, "OK!", noteDTO);
    }

    @Override
    public ResponseDTO getListExercise(InputCondition inputCondition) {
        List<ExerciseDTO> exerciseDTOList = exerciseRepository.getListByInputCondition(inputCondition);
        return new ResponseDTO(true, "OK!", exerciseDTOList);
    }

    @Override
    public ResponseDTO getDetailExercise(Long id) {
        ExerciseDTO exerciseDTO = exerciseRepository.getByID(id);
        return new ResponseDTO(true, "OK!", exerciseDTO);
    }

}
