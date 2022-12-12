//package com.example.springsecurity.service.impl;
//
//import com.example.springsecurity.model.User;
//import com.example.springsecurity.model.user_grade_point.UserGradePoint;
//import com.example.springsecurity.repo.UserRepo;
//import com.example.springsecurity.repo.user_grade_point.UserGradePointRepo;
//import com.example.springsecurity.service.UserGradePointService;
//import com.example.springsecurity.util.Utilities;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//@Slf4j
//public class UserGradePointImpl implements UserGradePointService {
//
//    private final UserRepo userRepo;
//    private final UserServiceImpl userService;
//
//    private final UserGradePointRepo userGradePointRepo;
//
//    @Override
//    public UserGradePoint saveUserPoint(UserGradePoint userGradePoint) {
//        UserGradePoint newUserGradePoint = new UserGradePoint();
//        newUserGradePoint.setUsername(Utilities.Util.getUsernameFromJwt());
//        User user = userRepo.findByUsername()
//        newUserGradePoint.setUser_id(user.getId());
//        newUserGradePoint.setMath12(userGradePoint.getMath12());
//        newUserGradePoint.setPhysic(userGradePoint.getPhysic());
//        newUserGradePoint.setChemistry(userGradePoint.getChemistry());
//        newUserGradePoint.setBiology(userGradePoint.getBiology());
//        newUserGradePoint.setLiterature(userGradePoint.getLiterature());
//        newUserGradePoint.setNn(userGradePoint.getNn());
//        newUserGradePoint.setHistory(userGradePoint.getHistory());
//        newUserGradePoint.setGeography(userGradePoint.getGeography());
//        newUserGradePoint.setEthics(userGradePoint.getEthics());
//        newUserGradePoint.setIt(userGradePoint.getIt());
//        newUserGradePoint.setEngineering(userGradePoint.getEngineering());
//        return null;
//    }
//
//    @Override
//    public List<UserGradePoint> getUserPoint() {
//        return null;
//    }
//
//    private boolean checkUsername(String userName){
//        User user = userService.findUsername(userName);
//        return user == null;
//    }
//
////    private boolean checkInput( String math12, String physic, String chemistry,
////                                String biology, String literature, String nn, Float hisotry, Float geography, Float ethics, Float it, Float engineering){
////        return !physic.equals("") && !math12.equals("") && !chemistry.equals("") && !biology.equals("") && !literature.equals("") && !nn.equals("");
////    }
//}
