//
//  ContentView.swift
//  Cats
//
//  Created by Jacob on 04.08.2023.
//

import SwiftUI

struct ContentView: View {
    @StateObject private var viewModel = CatImagesViewModel()
    @State private var selectedImage: CatImage?
    
    var body: some View {
        VStack(alignment: .leading) {
            ScrollView(.horizontal, showsIndicators: false) {
                HStack {
                    ForEach(viewModel.catImages) { catImage in
                        Image(uiImage: UIImage(url: catImage.url)!)
                            .resizable()
                            .scaledToFit()
                            .frame(width: 100, height: 100)
                            .padding(8)
                            .onTapGesture {
                                selectedImage = catImage
                            }
                    }
                }
            }.frame(maxHeight: 100)
            
            Spacer()
            
            if let selectedImage = selectedImage {
                Image(uiImage: UIImage(url: selectedImage.url)!)
                    .resizable()
                    .scaledToFit()
                    .padding(16)
            }
        }
        .padding(.top, 16)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

extension UIImage {
    convenience init?(url: URL) {
        do {
            let data = try Data(contentsOf: url)
            self.init(data: data)
        } catch {
            print("Failed to load image: \(error)")
            return nil
        }
    }
}
