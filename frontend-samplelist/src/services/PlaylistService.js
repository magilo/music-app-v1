import axios from 'axios';

const PLAYLIST_API_URL = 'http://localhost:8080/api/playlist/music';

class PlaylistService {

  async getCurrentMusicList() {
    try {
      const response = await axios.get(PLAYLIST_API_URL);
      // console.log('response', response);
      return response;
    } catch (error) {
      console.error(error);
    }
  }
}


export default new PlaylistService();
//export object of the class
