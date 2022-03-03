import React from 'react';
import PlaylistService from '../services/PlaylistService';

class PlaylistComponent extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      currentMusicList: []
    }
  }

  //lifecycle method called immediately after component is mounted
  async componentDidMount() {
    console.log("inside componentDidMount")
    const response = await PlaylistService.getCurrentMusicList();
    // const json = await response.json();
    console.log('response', response.data);
    this.setState({ currentMusicList: response.data });
    // PlaylistService.getCurrentMusicList().then((response) => {
    //   this.setState({ currentMusicList: response.data });
    // })
  }

  render() {
    console.log("playlistComponent state", this.state)
    return (
      <div>
        <h1 className="text-center"> Playlist </h1>
        <table className="table table-striped">
          <thead>
            <tr>
              <td>Title</td>
              <td>Artist</td>
              <td>Description</td>
              <td>Likes/Dislikes</td>
              <td>Play/Pause</td>
            </tr>

          </thead>
          <tbody>
            {
              this.state.currentMusicList.map(
                music =>
                  <tr key={music.name}>
                    <td> {music.title} </td>
                    <td> {music.name} </td>
                    <td> {music.description} </td>
                    <td> {music.likes}/{music.dislikes} </td>
                    <td> null </td>
                  </tr>
              )
            }
          </tbody>

        </table>
      </div>
    )
  }
}

export default PlaylistComponent;
