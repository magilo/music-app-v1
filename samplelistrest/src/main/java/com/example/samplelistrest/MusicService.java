package com.example.samplelistrest;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.samplelistrest.model.Music;
import com.example.samplelistrest.model.Playlist;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

@Service
public class MusicService {

  // private List<Music> musicList =
  // private Gson GSON = new GsonBuilder().setPrettyPrinting().create();

  private Playlist currentPlaylist = new Playlist("my current list");

  public JsonObject getInitialData() {
    String inline = "";
    // List<Object> sampleList = new ArrayList<>();
    // JSONArray jsonArray = new JSONArray();
    JsonObject sampleList = new JsonObject();

    try {
      URL url = new URL(
          "https://gist.githubusercontent.com/CervantesVive/3f85bf26672cf27fe1cd932ffcb7ecac/raw/4de50b351a62158083a97f3b950bd786d3ffd928/awesome-podcasts.json");

      // HttpURLConnection is a derived class of URLConnection
      // with extra methods like setRequestMethod
      // conn is should be renamed to request
      // connect to the URL using java's library
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.connect();
      int responseCode = conn.getResponseCode();

      if (responseCode != 200) {
        throw new RuntimeException("HttpResponseCode: " + responseCode);
      } else {

        // Scanner scanner = new Scanner(url.openStream());

        // while (scanner.hasNext()) {
        // inline += scanner.nextLine();
        // }

        // scanner.close();

        // Convert to a JSON object to print data
        // Convert the input stream to a json element
        InputStreamReader reader = new InputStreamReader((InputStream) conn.getContent());
        JsonElement data = JsonParser.parseReader(reader);
        JsonObject dataObj = data.getAsJsonObject();

        // JsonElement data_obj = JsonParser.parseString(inline);
        // JsonObject convertData = data_obj.getAsJsonObject();

        sampleList = dataObj;

      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {

    }
    return sampleList;
  }

  // public List<Music> getAllMusicList() {
  // return null;
  // }

  public Playlist getPlaylist() {
    return currentPlaylist;
  }

  public List<Music> getCurrentMusicList() {
    return currentPlaylist.getCurrentList();
  }

  public void addMusic(Music music) {
    // System.out.println(music.getDescription());
    // wait this just automatically turns it into music?
    // that's crazy.
    currentPlaylist.addMusic(music);
  }

  public void deleteMusic(String name) {
    currentPlaylist.deleteMusic(name);
    // change this to id when you have db
  }

  public void renamePlaylist(String name) {
    currentPlaylist.setName(name);
    System.out.println("rename " + currentPlaylist.getName());
  }

  public int like(String name) {
    return currentPlaylist.like(name);
    // change to id when you have db
  }

  public int dislike(String name) {
    return currentPlaylist.dislike(name);
  }

  public List<Music> sortByTitle() {
    return currentPlaylist.sortByTitle(currentPlaylist.getCurrentList());
  }

  public List<Music> sortByArtist() {
    return currentPlaylist.sortByArtist(currentPlaylist.getCurrentList());
  }

}
