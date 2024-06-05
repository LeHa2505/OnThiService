package cfm.onthi.services;

import cfm.onthi.dtos.CourseDTO;
import cfm.onthi.dtos.LessonDTO;
import cfm.onthi.dtos.ReviewDTO;
import cfm.onthi.dtos.ReviewUserDTO;
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
    ResponseDTO userLikeDislikeReview(ReviewDTO reviewDTO);
    ResponseDTO userUnlikeUndislikeReview(ReviewDTO reviewDTO);
    ResponseDTO userGetListReview(Long id);
    ResponseDTO userGetListComment(Long id);
    ResponseDTO userGetLessons(Long id);
    ResponseDTO userGetLessonDetail(Long id);
    ResponseDTO createReview(ReviewDTO reviewDTO);
    ResponseDTO deleteReview(Long  id);
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

    public CourseServiceImpl(
            TransactionTemplate transactionTemplate,
            CourseRepository courseRepository,
            UserRepository userRepository,
            LessonRepository lessonRepository,
            QuizRepository quizRepository,
            ReviewRepository reviewRepository,
            DocumentRepository documentRepository,
            NoteRepository noteRepository,
            ReviewUserRepository reviewUserRepository
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

}
