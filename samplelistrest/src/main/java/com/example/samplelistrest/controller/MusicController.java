package com.example.samplelistrest.controller;

import java.util.List;

import com.example.samplelistrest.MusicService;
import com.example.samplelistrest.model.Music;
import com.example.samplelistrest.model.Playlist;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// enables you to get json objects from each function without explicitly telling
// the function to return a json object
@RestController
@RequestMapping("api/")
public class MusicController {

  // enables you to inject the object dependency implicitly
  @Autowired
  private MusicService musicService;

  private Gson GSON = new GsonBuilder().setPrettyPrinting().create();

  @RequestMapping("/test")
  public JsonObject sampleData() {
    // String perfectJson = GSON.toJson(musicService.getInitialData());
    // System.out.println(musicService.getInitialData());
    return musicService.getInitialData();
  }

  // @RequestMapping("/music")
  // public List<Music> allMusic() {
  // return musicService.getAllMusicList();

  // }

  @GetMapping("playlist")
  public Playlist getPlaylist() {
    return musicService.getPlaylist();
  }

  @GetMapping("playlist/music")
  public List<Music> getCurrentMusicList() {
    return musicService.getCurrentMusicList();
  }

  @PostMapping("/playlist/music")
  public void addMusic(@RequestBody Music music) {
    musicService.addMusic(music);
    // put this in a try/catch?
  }

  @DeleteMapping("/playlist/music/{name}")
  public void deleteMusic(@PathVariable("name") String name) {
    musicService.deleteMusic(name);
    // change this to id when you have a db
  }

  @PatchMapping("/playlist")
  public void renamePlaylist(@RequestBody Playlist newName) {
    // System.out.println(newName.getName());
    musicService.renamePlaylist(newName.getName());
  }

  @PatchMapping("/playlist/music/{name}/like")
  public int addLike(@PathVariable("name") String name) {
    return musicService.like(name);
  }

  @PatchMapping("/playlist/music/{name}/dislike")
  public int addDislike(@PathVariable("name") String name) {
    return musicService.dislike(name);
  }

  @GetMapping("/playlist/music/by-title")
  public List<Music> sortByTitle() {
    return musicService.sortByTitle();
  }

  @GetMapping("/playlist/music/by-artist")
  public List<Music> sortByArtist(){
    return musicService.sortByArtist();
  }

}

/**
 * add throw exception later
 * @PatchMapping("/users/{id}")
 * public ResponseEntity<User> updateUserPartially(
 *
 * @PathVariable(value = "id") Long userId,
 * @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
 *        User user = userRepository.findById(userId)
 *        .orElseThrow(() -> new ResourceNotFoundException("User not found on ::
 *        "+ userId));
 *
 *        user.setEmailId(userDetails.getEmailId());
 *        user.setUpdatedAt(new Date());
 *        final User updatedUser = userRepository.save(user);
 *        return ResponseEntity.ok(updatedUser);
 *        }
 */
