#include <iostream>
#include <string>
#include <vector>
#include <functional>
#include <sstream>

using namespace std;


class File {
    protected:
        string _filename;
    
    public:
    int _filesize {-1};
    File(string fname, int fsize) : _filename(fname), _filesize(fsize) {
    }

    string get_filename() {
        return _filename;
    }

    virtual int total_size() {
        return _filesize;
    }

    virtual int filter_size(int size_limit) {
        return 0;
    }

    virtual File* delete_dir(int space_to_free) {
        return 0;
    }
};

class Directory : public File {
    private:
        vector<std::reference_wrapper<File>> files;
        Directory& _parent;

    public:

    Directory(string fname, int fsize, Directory& parent) : File(fname, fsize), _parent(parent) {}

    void add_file(File& f) {
        files.push_back(f);
    }

    Directory* get_parent_dir() {
        return &_parent;
    }

    Directory* find_dir(string dirname) {
        for (auto f: files) {
            if (f.get().get_filename() == dirname) {
                return dynamic_cast<Directory*>(&(f.get()));
            }
        }
        cout << "no dir name???" << endl;
        return NULL;
    }

    int total_size() {
        if (_filesize != -1) {
            return _filesize;
        }

        int size = 0;
        for (auto f : files) {
            size += f.get().total_size();
        }

        return size;
    }

    int filter_size(int size_limit) {

        int size = this->total_size();
        if (size > size_limit) {
            size = 0;
        }

        for (auto f : files) {
            size += f.get().filter_size(size_limit);
        }

        return size;
    }

    File* delete_dir(int space_to_free) {
        File* smallest_eligible_file {};
        int smallest_eligible_fsize {-1};

        for (auto f : files) {
            File* recommended_file = f.get().delete_dir(space_to_free);
            if (recommended_file != 0) {
                int recommended_fsize = recommended_file->total_size();
                if (smallest_eligible_file == 0) {
                    smallest_eligible_file = recommended_file;
                    smallest_eligible_fsize = recommended_fsize;
                } else if (recommended_fsize >= space_to_free && recommended_fsize < smallest_eligible_fsize) {
                    smallest_eligible_fsize = recommended_fsize;
                    smallest_eligible_file = recommended_file;
                }
            }
        }

        if (smallest_eligible_file == 0) {
            if (this->total_size() >= space_to_free) {
                return this;
            } else {
                return 0;
            }
        }

        return smallest_eligible_file;
    }
};

string process_ls(Directory *current_dir) {
    string inst;
    while(getline(cin, inst)) {
        // cout << inst << endl;

        if (inst.find("$") != string::npos) {
            return inst;
        }

        if (inst.substr(0,3) == "dir") {
            string dir_name = inst.substr(inst.find("dir") + 4);
            Directory *new_dir = new Directory(dir_name, -1, *current_dir);
            current_dir->add_file(*new_dir);
        } else {
            stringstream ss(inst);
            string buf;
            ss >> buf;
            int fsize = stoi(buf);
            ss >> buf; //name

            File *new_file = new File(buf, fsize);
            current_dir->add_file(*new_file);
        }
    }

    return inst;
}

int main(int argc, char const *argv[])
{
    bool skip {false};
    Directory *current_dir = NULL;
    Directory *root_dir;
    string inst;
    while (skip || getline(cin, inst))
    {
        skip = false;
        if (inst.find("cd") != string::npos) {
            string cd_to = inst.substr(inst.find("cd") + 3); //find target file name to cd to

            if (current_dir == NULL) {
                current_dir = new Directory(cd_to, -1, *current_dir);
                root_dir = current_dir;
            } else if (cd_to == "..") {
                current_dir = current_dir->get_parent_dir();
            } else {
                current_dir = current_dir->find_dir(cd_to);
            }
        } else if(inst.find("ls") != string::npos) {
            inst = process_ls(current_dir);
            skip = true;
        }

    }

    int total_size = root_dir->total_size();
    int size_above_100000 = root_dir->filter_size(100000);

    cout << "total size: " << total_size << endl;
    cout << "total size of files above 100000: " << size_above_100000 << endl;

    File* dir_to_delete = root_dir->delete_dir(total_size - 40000000);
    
    cout << "file to delete: " << dir_to_delete->get_filename() << endl;
    cout << "dir size: " << dir_to_delete->total_size() << endl;
    return 0;
    
}
