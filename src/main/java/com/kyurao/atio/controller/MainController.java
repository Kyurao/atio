package com.kyurao.atio.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/")
    public ModelAndView greeting() {
        return new ModelAndView("greeting");
    }

//    @GetMapping("/main")
//    public String main(Model model) {
//        Iterable<Lot> lots = lotRepository.findAllByStatusIn(Set.of(LotStatus.ACTIVE, LotStatus.SOLD));
//
//        model.addAttribute("lots" ,lots);
//
//        return "main";
//    }
//
//    @PostMapping("/main")
//    public String addLot(
//            @AuthenticationPrincipal User user,
//            Lot lot,
//            Model model,
//            @RequestParam("file") MultipartFile file
//            ) {
//        lot.setSeller(user);
//
//
//        return "main";
//    }
//
//    private void saveImage(Lot lot, Image image, @RequestParam("file") MultipartFile file) throws IOException {
//        if (file != null && !file.getOriginalFilename().isEmpty()) {
//
//            File uploadDir = new File(uploadPath);
//
//            if (!uploadDir.exists()) {
//                uploadDir.mkdir();
//            }
//
//            final String uuidFile = UUID.randomUUID().toString();
//            final String resultFileName = uuidFile + "." + image.getOriginalName();
//
//            file.transferTo(new File(uploadPath + "/" + resultFileName));
//
//            image.setOriginalName(resultFileName);
//            image.setPath(uploadPath);
//        }
//    }
}