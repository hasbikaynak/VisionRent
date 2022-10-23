package com.visionrent.controller;

import java.util.List;
import java.util.function.Function;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.visionrent.domain.ContactMessage;
import com.visionrent.dto.ContactMessageDTO;
import com.visionrent.dto.request.ContactMessageRequest;
import com.visionrent.dto.response.ResponseMessage;
import com.visionrent.dto.response.VRResponse;
import com.visionrent.mapper.ContactMessageMapper;
import com.visionrent.service.ContactMessageService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/contactmessage")
@AllArgsConstructor
public class ContactMessageController {

    private ContactMessageService contactMessageService;

    private ContactMessageMapper contactMessageMapper;

     // POST  localhost:8080/contactmessage/visitors
    @PostMapping("/visitors")
    public ResponseEntity<VRResponse> createMessage(@Valid @RequestBody ContactMessageRequest contactMessageRequest){

        ContactMessage contactMessage = contactMessageMapper.contactMessageRequestToContactMessage(contactMessageRequest);
        contactMessageService.saveMessage(contactMessage);

        VRResponse response=new VRResponse(ResponseMessage.CONTACTMESSAGE_SAVE_RESPONSE_MESSAGE, true);

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    // GET localhost:8080/contactmessage
    @GetMapping
    public ResponseEntity<List<ContactMessageDTO>> getAllContactMessage(){
        List<ContactMessage> contactMessageList= contactMessageService.getAll();
        List<ContactMessageDTO> contactMessageDTOList = contactMessageMapper.map(contactMessageList);

        return ResponseEntity.ok(contactMessageDTOList);

    }

    // GET  localhost:8080/contactmessage/pages?page=0&size=2&sort=name&direction=ASC
    @GetMapping("/pages")
    public ResponseEntity<Page<ContactMessageDTO>> getAllContactMessageWithPage(@RequestParam("page") int page,@RequestParam("size") int size,
                                                                                @RequestParam("sort") String prop,
                                                                                @RequestParam(value="direction",required=false,defaultValue = "DESC") Direction direction){
        Pageable pageable=PageRequest.of(page, size,Sort.by(direction,prop));
        Page<ContactMessage> contactMessagePage = contactMessageService.getAll(pageable);

        //Page<ContactMessage> -> Page<ContactMessageDTO>
        Page<ContactMessageDTO> pageDTO = getPageDTO(contactMessagePage);

        return ResponseEntity.ok(pageDTO);
    }

    // GET localhost:8080/contactmessage/2
    @GetMapping("/{id}")
    public ResponseEntity<ContactMessageDTO> getMessageWithPath(@PathVariable("id") Long id){
        ContactMessage contactMessage = contactMessageService.getContactMessage(id);
        ContactMessageDTO contactMessageDTO = contactMessageMapper.contactMessageToDTO(contactMessage);

        return ResponseEntity.ok(contactMessageDTO);
    }

    // GET localhost:8080/contactmessage/request?id=2
    @GetMapping("/request")
    public ResponseEntity<ContactMessageDTO> getMessageWithRequestParam(@RequestParam("id") Long id){
        ContactMessage contactMessage = contactMessageService.getContactMessage(id);
        ContactMessageDTO contactMessageDTO = contactMessageMapper.contactMessageToDTO(contactMessage);

        return ResponseEntity.ok(contactMessageDTO);
    }

    // DELETE localhost:8080/contactmessage/1
    @DeleteMapping("/{id}")
    public ResponseEntity<VRResponse> deleteContactMessage(@PathVariable Long id){
        contactMessageService.deleteContactMessage(id);

        VRResponse vrResponse=new VRResponse(ResponseMessage.CONTACTMESSAGE_DELETE_RESPONSE_MESSAGE, true);
        return ResponseEntity.ok(vrResponse);
    }

    // PUT localhost:8080/contactmessage/3
    @PutMapping("/{id}")
    public ResponseEntity<VRResponse> updateContactMessage(@PathVariable Long id,@Valid @RequestBody ContactMessageRequest contactMessageRequest ){

        ContactMessage contactMessage= contactMessageMapper.contactMessageRequestToContactMessage(contactMessageRequest);

        contactMessageService.updateContactMessage(id, contactMessage);

        VRResponse vrResponse=new VRResponse(ResponseMessage.CONTACTMESSAGE_UPDATE_RESPONSE_MESSAGE, true);
        return ResponseEntity.ok(vrResponse);
    }

    private Page<ContactMessageDTO> getPageDTO(Page<ContactMessage>  contactMessagePage){
        Page<ContactMessageDTO> dtoPage= contactMessagePage.map( new Function<ContactMessage, ContactMessageDTO>() {
            @Override
            public ContactMessageDTO apply(ContactMessage contactMessage) {
                return contactMessageMapper.contactMessageToDTO(contactMessage);
            }
        });
        return dtoPage;
    }
}
