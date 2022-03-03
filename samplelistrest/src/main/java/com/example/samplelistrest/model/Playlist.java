package com.example.samplelistrest.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Playlist {

  private String name;
  private List<Music> currentList = new ArrayList<>();
  static private Comparator<Music> ascTitle;
  static private Comparator<Music> ascArtist;

  static {

    ascTitle = new Comparator<Music>() {
      @Override
      public int compare(Music m1, Music m2) {
        return m1.getTitle().compareTo(m2.getTitle());
      }
    };

    ascArtist = new Comparator<Music>() {
      @Override
      public int compare(Music m1, Music m2) {
        return m1.getName().compareTo(m2.getName());
      }
    };

  }

  public Playlist() {

  }

  public Playlist(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Music> getCurrentList() {
    return currentList;
  }

  public void setCurrentList(List<Music> currentList) {
    this.currentList = currentList;
  }

  public void addMusic(Music music) {
    this.currentList.add(music);

  }

  public void deleteMusic(String name) {
    this.currentList.removeIf(music -> music.getName().equals(name));
  }

  public int like(String name) {
    Music currentMusic = this.currentList.stream().filter(music -> music.getName().equals(name)).findFirst().get();
    currentMusic.setLikes(currentMusic.getLikes() + 1);
    return currentMusic.getLikes();
    // System.out.println(currentMusic.getLikes());
  }

  public int dislike(String name) {
    Music currentMusic = this.currentList.stream().filter(music -> music.getName().equals(name)).findFirst().get();
    currentMusic.setDislikes(currentMusic.getDislikes() + 1);
    return currentMusic.getDislikes();
  }

  public List<Music> sortByTitle(List<Music> currentList) {
    // System.out.println(currentList);
    Collections.sort(currentList, ascTitle);
    return currentList;
  }

  public List<Music> sortByArtist(List<Music> currentList) {
    Collections.sort(currentList, ascArtist);
    return currentList;
  }
}
