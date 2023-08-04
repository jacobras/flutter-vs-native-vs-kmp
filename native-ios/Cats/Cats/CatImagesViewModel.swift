//
//  CatImagesViewModel.swift
//  Cats
//
//  Created by Jacob on 04.08.2023.
//

import Foundation
import Alamofire

class CatImagesViewModel: ObservableObject {
    @Published var catImages: [CatImage] = []
    
    init() {
        fetchCatImages()
    }
    
    func fetchCatImages() {
        let apiUrl = "https://api.thecatapi.com/v1/images/search?limit=10"
        AF.request(apiUrl).responseJSON { response in
            if let data = response.data {
                do {
                    let json = try JSONSerialization.jsonObject(with: data, options: [])
                    if let jsonArray = json as? [[String: Any]] {
                        let images = jsonArray.compactMap { json -> CatImage? in
                            guard let id = json["id"] as? String,
                                  let urlString = json["url"] as? String,
                                  let url = URL(string: urlString) else {
                                return nil
                            }
                            return CatImage(id: id, url: url)
                        }
                        DispatchQueue.main.async {
                            self.catImages = images
                        }
                    }
                } catch {
                    print("Failed to parse JSON: \(error)")
                }
            }
        }
    }
}
